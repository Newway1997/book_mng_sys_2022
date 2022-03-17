package com.bookmngsys.controller;

import com.bookmngsys.domain.ReaderDto;
import com.bookmngsys.domain.UserDto;
import com.bookmngsys.entity.Reader;
import com.bookmngsys.entity.User;
import com.bookmngsys.service.ReaderService;
import com.bookmngsys.tool.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Newway
 */
@Controller
@RequestMapping("/api/reader")
public class ReaderController {
    @Autowired
    ReaderService readerService;

    @ApiOperation("获取读者信息")
    @GetMapping(value = "/getReaderInfo")
    @ResponseBody
    public Response getReaderInfo(@RequestAttribute("user") UserDto userDto) {
        Reader reader = readerService.getReader(userDto.getId());
        if (reader != null) {
            return new Response().success(ReaderDto.convertToReaderDto(reader));
        } else {
            return new Response().failure("未找到该读者");
        }
    }

    @ApiOperation("更新读者信息")
    @PostMapping(value = "/updateReaderInfo")
    @ResponseBody
    public Response updateReaderInfo(@RequestBody Reader reader, @RequestAttribute("user") UserDto userDto) {
        reader.setId(userDto.getId());
        readerService.updateReader(reader);
        return new Response().success();

    }

    @ApiOperation("获取所有读者")
    @GetMapping("/getAllReaders")
    @ResponseBody
    public Response getReaders(@RequestParam int curPage, @RequestParam int pageSize) {
        List<Reader> readers = readerService.getReaders(curPage, pageSize);
        return new Response().success(readers.stream().map(ReaderDto::convertToReaderDto));
    }
}
