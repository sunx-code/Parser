package com.sunx.parser;

import org.junit.Test;

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
}
