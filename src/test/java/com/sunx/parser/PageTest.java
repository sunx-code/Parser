package com.sunx.parser;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 *
 */
public class PageTest {

    @Test
    public void test(){
        String src = "<div class='dev' data-pid='123'><a href='#'>2</a><a href='#' md='123' >2</a></div>";

        Page page = Page.me().bind(src);

        String pid = page.css("div.dev a",1,"md");
        System.out.println(pid);
    }

    @Test
    public void test1() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("/User/sunx/.bash_profile"))));

        String line = null;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }
    }
    @Test
    public void testClean(){
        String html = "<span>---</span>";
        Page page = Page.me().bind(html);
        page.clean("span");

        System.out.println(page.html("body"));
    }
}
