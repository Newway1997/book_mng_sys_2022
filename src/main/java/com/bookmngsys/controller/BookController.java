package com.bookmngsys.controller;

import com.bookmngsys.domain.BookDto;
import com.bookmngsys.domain.BorrowBookDto;
import com.bookmngsys.domain.UserDto;
import com.bookmngsys.entity.Book;
import com.bookmngsys.entity.BorrowBook;
import com.bookmngsys.entity.Category;
import com.bookmngsys.service.BookService;
import com.bookmngsys.tool.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Newway
 */
@Api("书籍管理")
@Controller
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @ApiOperation("获取书籍列表")
    @GetMapping("/getBooks")
    @ResponseBody
    public Response getBooks(@RequestParam Integer curPage, @RequestParam Integer pageSize, HttpSession httpSession) {
        UserDto userDto = (UserDto) httpSession.getAttribute("USER_SESSION");
        List<Book> books = bookService.getBooks(curPage, pageSize, userDto != null && userDto.isAdmin());
        return new Response().success(books.stream().map(BookDto::convertToBookDto));
    }

    @ApiOperation("获取书籍详情")
    @GetMapping("/getBookDetail")
    @ResponseBody
    public Response getBook(@RequestParam String isbn) {
        Book book = bookService.getBook(isbn);
        return new Response().success(BookDto.convertToBookDto(book));
    }

    @ApiOperation("借书")
    @GetMapping("/borrowBook")
    @ResponseBody
    public Response borrowBook(@RequestParam String isbn, @RequestAttribute int userId) {
        try {
            bookService.borrowBook(isbn, userId);
        } catch (Exception e) {
            return new Response().failure(e.getMessage());
        }
        return new Response().success();
    }

    @ApiOperation("返还书籍")
    @GetMapping("/returnBook")
    @ResponseBody
    public Response returnBook(@RequestParam String isbn, @RequestParam int id, @RequestAttribute int userId) {
        //按isbn借阅图书
        bookService.returnBook(id, isbn, userId);
        return new Response().success();
    }

    @ApiOperation("获取借书记录")
    @GetMapping("/getBorrowBooks")
    @ResponseBody
    public Response getBorrowBooks(@RequestAttribute int userId) {
        List<BorrowBook> borrowBooks = bookService.getBorrowBooks(userId);
        return new Response().success(borrowBooks.stream().map(BorrowBookDto::convertToBorrowBookDto));
    }


    @ApiOperation("启用图书")
    @GetMapping("/enableBook")
    @ResponseBody
    public Response enableBook(@RequestParam String isbn, @RequestAttribute("user") UserDto userDto) {
        if (!userDto.isAdmin()) {
            return new Response().failure("当前用户没有权限");
        }
        bookService.enableBook(isbn);
        return new Response().success();
    }

    @ApiOperation("禁用图书")
    @GetMapping("/disableBook")
    @ResponseBody
    public Response disableBook(@RequestParam String isbn, @RequestAttribute("user") UserDto userDto) throws IllegalStateException, IOException {
        //按isbn借阅图书
        if (!userDto.isAdmin()) {
            return new Response().failure("当前用户没有权限");
        }
        bookService.disableBook(isbn);
        return new Response().success();
    }

    @ApiOperation("获取书籍分类")
    @GetMapping("/getCategory")
    @ResponseBody
    public Response getCategory() {
        List<Category> categories = bookService.getCategory();
        return new Response().success(categories);

    }

    @ApiOperation("上传书籍")
    @PostMapping(value = "/uploadBook")
    @ResponseBody
    public Response uploadBook(@ModelAttribute BookDto bookDto, @RequestParam(value = "img", required = false) MultipartFile file) {
        Book existBook = bookService.getBook(bookDto.getIsbn());
        if (existBook == null) {
            return new Response().failure("该isbn号已存在");
        }
        String imgUrl = file != null ? file.getOriginalFilename() : "";
        bookDto.setImgUrl(imgUrl);
        bookService.addBook(BookDto.convertToBook(bookDto));
        if (file != null) {
            return saveFileAndResponse(file);
        }
        return new Response().success();
    }

    @ApiOperation("更新书籍信息")
    @PostMapping(value = "/updateBook")
    @ResponseBody
    public Response updateBook(@RequestParam String originIsbn, @ModelAttribute BookDto bookDto, @RequestParam(value = "img", required = false) MultipartFile file) {
        String imgUrl = file != null ? file.getOriginalFilename() : "";
        bookDto.setImgUrl(imgUrl);
        bookDto.setLastNum(bookDto.getAllNum());
        bookService.updateBook(originIsbn, BookDto.convertToBook(bookDto));
        if (file != null) {
            return saveFileAndResponse(file);
        }
        return new Response().success();
    }

    private Response saveFileAndResponse(MultipartFile file) {
        String path = ClassUtils.getDefaultClassLoader().getResource("img").getPath() + "/" + file.getOriginalFilename();
        try {
            file.transferTo(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
            return new Response().failure("图片读取错误");
        }
        return new Response().success();
    }
}
