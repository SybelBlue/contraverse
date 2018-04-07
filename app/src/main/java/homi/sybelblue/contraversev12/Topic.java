package homi.sybelblue.contraversev12;

import java.util.ArrayList;

import homi.sybelblue.contraversev12.questions.SpecificQuestion;

public class Topic extends ArrayList<SpecificQuestion> {

    public String name;

    public Topic(String name) {
        this.name = name;
    }

    @Override
    public void add(int index, SpecificQuestion element) {
        super.add(index, element);
        element.topicHeading = name;
    }

    @Override
    public boolean add(SpecificQuestion specificQuestion) {
        boolean b = super.add(specificQuestion);
        if (b) specificQuestion.topicHeading = name;
        return b;
    }
}
