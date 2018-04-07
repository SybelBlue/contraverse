package homi.sybelblue.contraversev12;

import java.util.ArrayList;
import java.util.Collection;

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

    @Override
    public boolean addAll(Collection<? extends SpecificQuestion> c) {
        for (SpecificQuestion specificQuestion : c)
            specificQuestion.topicHeading = name;
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends SpecificQuestion> c) {
        for (SpecificQuestion specificQuestion : c)
            specificQuestion.topicHeading = name;
        return super.addAll(index, c);
    }
}
