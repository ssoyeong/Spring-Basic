package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    // 웹 어플리케이션에서 /hello가 들어오면 해당 method를 호출
    public String hello(Model model){   // MVC의 model
        model.addAttribute("data", "hello!!");
        return "hello";     // templates/hello.html 을 spring이 찾아서 thymelaef 템플릿 엔진이 처리함

    }

    @GetMapping("hello-mvc")    // html 형식으로 전달
    public String helloMvc(@RequestParam("name") String name, Model model) {  // 이 model에 담으면, view에서 렌더링할 때 사용
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // http의 body부에 이 data를 직접 넣어주겠다. html 형식이 아닌 그냥 data 전달
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody   // 이게 안붙어 있으면 viewResolver한테 던져서 템플릿을 반환하도록 함
                    // 근데 http 응답에 그대로 이 데이터를 넘겨야겠구나 하고 동작. 근데 객체인 경우, default는 JSON 방식으로 반환.
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // 문자가 아닌 객체를 넘김. JSON 방식으로 반환
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
