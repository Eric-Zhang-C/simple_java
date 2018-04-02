package hello;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class WordladderController {
    @Autowired
    private WordladderService wordladderService;

    @RequestMapping(value = "/wordladder", method = RequestMethod.GET)
    public ArrayList<String> wordLadder(String start, String end) {
        System.out.println("controller");
        return wordladderService.wordLadder(start, end);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        System.out.println("test");
        return "test";
    }
}
