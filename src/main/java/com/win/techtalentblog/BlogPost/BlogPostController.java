package com.win.techtalentblog.BlogPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BlogPostController {

    @Autowired
    private static BlogPostRepository blogPostRepository;

    private static List<BlogPost> posts = new ArrayList<>();
    private BlogPost blogPost;

    public static void getAllPosts() {

        FakeBlogInfo fakePosts = FakeBlogInfo.getInstance();
        for (BlogPost fakePost : fakePosts.allFakeBlogs()) {
            posts.add(fakePost);
            for (BlogPost post : posts) {
                blogPostRepository.save(post);
            }
        }

        // Iterable<BlogPost> allPosts = blogPostRepository.findAll();

        // int numPosts = 0;
        // for (Object i : allPosts) {
        // numPosts++;
        // }

        // if (numPosts > 0) {
        // for (BlogPost post : blogPostRepository.findAll()) {
        // posts.add(post);
        // }
        // } else {
        // FakeBlogInfo fakePosts = FakeBlogInfo.getInstance();
        // for (BlogPost fakePost : fakePosts.allFakeBlogs()) {
        // posts.add(fakePost);
        // }
        // }
    }

    @GetMapping(value = "/")
    public String index(BlogPost blogPost, Model model) {
        // Run getAllPosts() again to make sure our index has all of the posts in the
        // database
        getAllPosts();
        // Put all of the posts we just grabbed into our model for Thymeleaf to deal
        // with
        model.addAttribute("posts", posts);
        return "blogpost/index";
    }

    @GetMapping(value = "/blogposts/new")
    public String newBlog(BlogPost blogPost) {
        return "blogpost/new";
    }

    @GetMapping(value = "/blogposts/{id}")
    public String getBlogById(@PathVariable String id, Model model) {
        long blogId = Long.parseLong(id);
        Optional<BlogPost> blogPost = blogPostRepository.findById(blogId);
        if (blogPost.isPresent()) {
            BlogPost post = blogPost.get();
            model.addAttribute("title", post.getTitle());
            model.addAttribute("author", post.getAuthor());
            model.addAttribute("blogEntry", post.getBlogEntry());
            return "blogpost/new";
        } else {
            throw new NullPointerException();
        }

    }

    @PostMapping(value = "/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));

        // Add new blog posts as they're created to our posts list for indexing
        // ** I think is this unneeded now
        // posts.add(blogPost);

        // Add attributes to our model so we can show them to the user on the results
        // page
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
    }
}