package homi.sybelblue.contraversev12;

import java.util.ArrayList;
import java.util.Collection;

import homi.sybelblue.contraversev12.questions.SpecificQuestion;

public class SpecificQuestion extends ArrayList<homi.sybelblue.contraversev12.questions.SpecificQuestion> {

    public String name;

    public SpecificQuestion(String name) {
        this.name = name;
    }

    @Override
    public void add(int index, homi.sybelblue.contraversev12.questions.SpecificQuestion element) {
        super.add(index, element);
        element.topicHeading = name;
    }

    @Override
    public boolean add(homi.sybelblue.contraversev12.questions.SpecificQuestion specificQuestion) {
        boolean b = super.add(specificQuestion);
        if (b) specificQuestion.topicHeading = name;
        return b;
    }

    @Override
    public boolean addAll(Collection<? extends homi.sybelblue.contraversev12.questions.SpecificQuestion> c) {
        for (homi.sybelblue.contraversev12.questions.SpecificQuestion specificQuestion : c)
            specificQuestion.topicHeading = name;
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends homi.sybelblue.contraversev12.questions.SpecificQuestion> c) {
        for (homi.sybelblue.contraversev12.questions.SpecificQuestion specificQuestion : c)
            specificQuestion.topicHeading = name;
        return super.addAll(index, c);
    }
}
