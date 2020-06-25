package com.win.techtalentblog.BlogPost;

import java.util.ArrayList;
import java.util.List;

public class FakeBlogInfo {
    private List<BlogPost> blogPosts;

    private static FakeBlogInfo fakeBlogPosts = null;

    public static FakeBlogInfo getInstance() {
        if (fakeBlogPosts == null) {
            fakeBlogPosts = new FakeBlogInfo();
        }
        return fakeBlogPosts;
    }

    public FakeBlogInfo() {
        blogPosts = new ArrayList<BlogPost>();

        blogPosts.add(new BlogPost("First Blog", "Zach", "Testing 1-2-3"));
        blogPosts.add(new BlogPost("Second Blog", "Kaley", "Hello, world!"));
        blogPosts.add(new BlogPost("Third Blog", "George", "I'm having so much fun!"));
    }

    public Iterable<BlogPost> allFakeBlogs() {
        return blogPosts;
    }
}