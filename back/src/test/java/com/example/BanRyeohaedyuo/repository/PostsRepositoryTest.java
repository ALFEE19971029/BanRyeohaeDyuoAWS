package com.example.BanRyeohaedyuo.repository;

import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.enumtype.Category;
import com.example.BanRyeohaedyuo.repository.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void findPostsTest() {

        String title = "테스트 title";
        String description = "테스트 description";
        String place = "테스트 place";
        String image1 = "테스트 image1";
        String image2 = "테스트 image2";
        String category = "테스트 category";
        String date = "테스트 date";
        int point = 111;

        postsRepository.save(Posts.builder()
                .title(title)
                .description(description)
                .image1(image1)
                .image2(image2)
                .category(Category.ANIMAL)
                .point(point)
                .build()
    );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is(title));//expected, actual

    }
}
