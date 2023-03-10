package com.example.BanRyeohaedyuo.controller;

import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.embedded.Address;
import com.example.BanRyeohaedyuo.domain.enumtype.Category;
import com.example.BanRyeohaedyuo.domain.enumtype.Grade;
import com.example.BanRyeohaedyuo.domain.enumtype.Purpose;
import com.example.BanRyeohaedyuo.repository.KakaoUserRepository;
import com.example.BanRyeohaedyuo.repository.PostsRepository;
import com.example.BanRyeohaedyuo.controller.dto.posts.PostsSaveRequestDto;
import com.example.BanRyeohaedyuo.controller.dto.posts.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.*; //추가 부분.
import static org.hamcrest.Matchers.is;

@EnableWebMvc //스프링이 자동으로 제공하는 웹과 관련된 최신 빈들을 setting.
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private KakaoUserRepository kakaoUserRepository;
    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_create() throws Exception{
        Long userId = 12345L;
        String title = "hello";
        String description = "hi";
        Address address = new Address("고양","강석","503");
        String image1 = "5160";
        String image2 = "6146";
        String image3 = "5316510";
        String category = "ANIMAL";
        String purpose = "FINDING";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title).description(description).address(address).image2(image2).image1(image1)
                .image3(image3).category(category).purpose(purpose).build();


        String url = "http://localhost:" + port + "/api/posts";

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestDto, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getTitle(), is(title));
        System.out.println(all.get(0).toString());

    }

    @Test
    public void Posts_update() throws Exception{
        KakaoUser testUser = KakaoUser.builder().kakaoId("test1").kakaoProfileImg("test2")
                .kakaoNickname("test3").kakaoEmail("test4").userRole("test5").grade(Grade.SEMIPRO).point(10).build();
        kakaoUserRepository.save(testUser);
        Address address = Address.builder().city("고양").street("강석로").detailAddress("700").build();
        Posts savedPosts = postsRepository.save(Posts.builder().kakaoUser(testUser).title("savedPosts").description("saved")
                .address(address).image1("123").image2("456").image3("789").category(Category.ANIMAL).point(0).purpose(Purpose.FINDING)
                .build());

        Long updateId = savedPosts.getPostsId();
        String title = "update";
        String description = "update";
        Address newAddress = Address.builder().city("서울").street("선릉").detailAddress("204").build();
        String image1 = "000";
        String image2 = "111";
        String image3 = "222";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(title)
                .description(description)
                .address(newAddress)
                .image1(image1)
                .image2(image2)
                .image3(image3)
                .category("THINGS")
                .build();

        String url = "http://localhost:" + port + "/api/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo("update");

    }
}
