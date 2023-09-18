package com.carrot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carrot.domain.AuthVO;
import com.carrot.domain.UserVO;
import com.carrot.handler.CustomUser;
import com.carrot.repository.UserRepository;
import com.carrot.service.NaverLogin;
import com.carrot.service.UserService;
import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SnsLoginController {
	@Autowired
	private NaverLogin naverLogin;
	private String apiResult = null;
	@Autowired
	private UserService service;
	@Autowired
	private SqlSession sqlSession;

//	네이버 로그인
	@RequestMapping("/api/signup/callback/naver")
	public String naverCallback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session, RedirectAttributes redirectAttr)
			throws IOException, ParseException {
		OAuth2AccessToken oauthToken;
		oauthToken = naverLogin.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLogin.getUserProfile(oauthToken); // String형식의 json데이터
		// System.out.println(apiResult);
		/**
		 * apiResult json 구조 {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response의 nickname값 파싱
		
		// 네이버에서 주는 고유 ID  Z7gumZvEzbYsMemoKaC9uba6OYIioMgDi6KB3CMntQE
		String id = (String) response_obj.get("id"); 
	    // 네이버에서 설정된 사용자 이름
		String name = (String) response_obj.get("name");
		// 네이버에서 설정된 이메일
		String email = (String) response_obj.get("email");
		
		//id는 고유ID로 넘어 오긴 하지만 SNS서비스 이름을 식별하기 위해서 새로운 ID로 만들어 줬다.
		String newId = "NAVER_"+ id ;
        //네이버 같은경우 ID는 안주기때문에 연락처의 이메일을 문자열 잘라서 id값을 추출하는 작업을 펼친다. 초기 닉네임에 사용할거다
		Random random = new Random();
		int ranNum =  (random.nextInt(888889) + 111111);
		String ranNumStr = String.valueOf(ranNum);
		String target ="@";
        int target_num = email.indexOf(target);
        String newNic = "NAVER_"+ (String)email.substring(0,target_num) + ranNumStr;

		UserVO vo = new UserVO();
		List<AuthVO> authlist = new ArrayList<AuthVO>();
		AuthVO authVo = new AuthVO();
		UUID uuid = UUID.randomUUID();
		authVo.setUser_id(newId);
		authVo.setAuth("ROLE_MEMBER");
		authlist.add(authVo);

		vo.setId(newId);
		vo.setAuthList(authlist);
		vo.setPassword(uuid.toString());
		vo.setNickname(newNic);
		vo.setEmail(email);
		// db에 해당 유저가 없을경우 join
		if (service.idCheck(newId) != 1) {
			service.signUp(vo);
			service.signUp_auth(authVo);
		}else {
			UserRepository mapper = sqlSession.getMapper(UserRepository.class);
			UserVO dataVo = mapper.selectById(newId);
			if(dataVo.getLoc1() != null) vo.setLoc1(dataVo.getLoc1()); 
			if(dataVo.getLoc2() != null) vo.setLoc2(dataVo.getLoc2()); 
			if(dataVo.getLoc3() != null) vo.setLoc3(dataVo.getLoc3()); 
		}
		CustomUser customUser = new CustomUser(vo);
		
		

		System.out.println("커스텀유저:"+customUser);
		// 시큐리티 권한을 직접 세팅함
		Authentication authentication = new UsernamePasswordAuthenticationToken(customUser, null,
				customUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		System.out.println("loc모음집:"+vo.getLoc1()+vo.getLoc2()+vo.getLoc3());
		if(vo.getLoc1() == null || vo.getLoc2() == null || vo.getLoc3() == null) {
			redirectAttr.addFlashAttribute("req_locRegist", "loc_isNull");
					System.out.println("if문 안");
					return "redirect:/api/success";
//					 return "redirect:/api/success/{req_locRegist}";
					 //마이페이지 - 나의 정보 수정으로 갈것 /redirect 해야할수도. 아래내용 jsp추가
//						if('${req_locRegist}' == "loc_isNull"){
//							alert("지역 등록되어있지 않슴다 등록해주세요");
//							window.location.href = "/page/listItem";
//		
		}
		System.out.println("if문 밖");
		return "redirect:/page/listItem"; // 이미 지역정 등록되어있는 사람이니까 메인으로 보내도됨
	}
	
	@RequestMapping(value="/api/callback/sns/kakao", method=RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code) throws Exception {
		System.out.println("#########" + code);
		return "member/testPage";
		/*
		 * 리턴값의 testPage는 아무 페이지로 대체해도 괜찮습니다.
		 * 없는 페이지를 넣어도 무방합니다.
		 * 404가 떠도 제일 중요한건 #########인증코드 가 잘 출력이 되는지가 중요하므로 너무 신경 안쓰셔도 됩니다.
		 */
    	}
	
	
}