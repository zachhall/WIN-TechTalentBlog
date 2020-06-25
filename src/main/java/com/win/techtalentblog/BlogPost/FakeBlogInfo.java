package com.win.techtalentblog.BlogPost;

import java.util.ArrayList;
import java.util.List;

public class FakeBlogInfo {
    private static List<BlogPost> blogPosts;

    private static FakeBlogInfo fakeBlogPosts = null;

    public static FakeBlogInfo getInstance() {
        if (fakeBlogPosts == null) {
            fakeBlogPosts = new FakeBlogInfo();
        }
        return fakeBlogPosts;
    }

    public FakeBlogInfo() {
        blogPosts = new ArrayList<BlogPost>();

        blogPosts.add(new BlogPost("First Blog", "Zach", "Hello, world!"));
        blogPosts.add(new BlogPost("Second Blog", "Kaley", "Testing, 1-2-3"));
        blogPosts.add(new BlogPost("Third Blog", "George", "Here we go!"));
    }

    public static List<BlogPost> allBlogs() {
        return blogPosts;
    }
}