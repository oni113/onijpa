package oni.onijpa.model;

import org.springframework.web.bind.annotation.ModelAttribute;

public class Human {
    private String name;

    public Human() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
