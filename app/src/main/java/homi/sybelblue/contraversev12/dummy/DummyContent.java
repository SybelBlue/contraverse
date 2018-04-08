package homi.sybelblue.contraversev12.dummy;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import homi.sybelblue.contraversev12.Convo;
import homi.sybelblue.contraversev12.User;
import homi.sybelblue.contraversev12.activities.MainActivity;
import homi.sybelblue.contraversev12.questions.Prompt;
import homi.sybelblue.contraversev12.questions.Response;
import homi.sybelblue.contraversev12.questions.SpecificQuestion;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }


    public static Convo makePuppyConvo() {
        User u1 = MainActivity.currentUser, u2 = new User(10000001,new int[0]);
        u2.name = "John Doe";
        Prompt<Integer> p1 = new Prompt<Integer>("How many puppy kisses do you want?");
        p1.setResponse(new Response<Integer>(u1,0));
        Prompt<String> p2 = new Prompt<>("Do you like slobber?");
        p2.setResponse(new Response<>(u1,"Of course not, but I love puppies!"));

        return new Convo(new Date(),u1,u2,
                new SpecificQuestion(p1,p2,(short)0),
                new Response<String>(u1, "I have 4 dogs"),
                new Response<String>(u1, "How many dogs do you have?"),
                new Response<String>(u1, "I don't have any. They destroy the furniture"));
    }


    public static Convo makeHotDogConvo() {
        User u1 = MainActivity.currentUser, u2 = new User(10000001,new int[0]);
        u2.name = "John Doe";
        Prompt<Integer> p1 = new Prompt<Integer>("How many puppy kisses do you want?");
        p1.setResponse(new Response<Integer>(u1,0));
        Prompt<String> p2 = new Prompt<>("Do you like slobber?");
        p2.setResponse(new Response<>(u1,"Of course not, but I love puppies!"));

        return new Convo(new Date(),u1,u2,
                new SpecificQuestion(p1,p2,(short)0),
                new Response<String>(u1, "I have 4 dogs"),
                new Response<String>(u1, "How many dogs do you have?"),
                new Response<String>(u1, "I don't have any. They destroy the furniture"));
    }


    public static Convo makeCoffeeTeaConvo() {
        User u1 = MainActivity.currentUser, u2 = new User(10000001,new int[0]);
        u2.name = "John Doe";
        Prompt<Integer> p1 = new Prompt<Integer>("How many puppy kisses do you want?");
        p1.setResponse(new Response<Integer>(u1,0));
        Prompt<String> p2 = new Prompt<>("Do you like slobber?");
        p2.setResponse(new Response<>(u1,"Of course not, but I love puppies!"));

        return new Convo(new Date(),u1,u2,
                new SpecificQuestion(p1,p2,(short)0),
                new Response<String>(u1, "I have 4 dogs"),
                new Response<String>(u1, "How many dogs do you have?"),
                new Response<String>(u1, "I don't have any. They destroy the furniture"));
    }
}
