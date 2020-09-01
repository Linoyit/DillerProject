package com.example.android.diller10threunion;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListFactory {

    static final String USER_NAME = "user name";
    static final String TOTAL_QUESTIONS = "total questions";
    static final String CORRECT_ANSWERS = "correct answers";

    QuestionsListFactory(){}

    public List<Question> getQuestions(){

        List<Question> questions = new ArrayList<>();

        Question q1 = new Question(
                1,
                "איפה לא הינו עם האמריקאים בארץ?",
                R.drawable.desert,
                new String[]{"בחוף הים", "בים המלח", "בבית קנדה", "בנגב"},
                2, R.raw.video,null);

        Question q2 = new Question(
                2,
                "איפה לא הינו עם האמריקאים בארהב?",
                R.drawable.pic1,
                new String[]{"מצפה תת ימי", "בית כנסת יהודי", "מופע דולפינים", "ברודוואי"},
                4, null, null);

        Question q3 = new Question(
                3,
                "מיד אחרי איזו פעילות אכלנו את הגלידה הזאת?",
                R.drawable.ice_cream,
                new String[]{"בית ספר", "המצפה התת ימי", "הטיימס סקוור", "התנדבות בבית אבות"},
                2, null, null);

        Question q4 = new Question(
                4,
                "איזה שיר שרנו בתמונה?",
                R.drawable.song,
                new String[]{"כד קטן", "תנו לשמש לעלות", "הבאנו שלום עליכם", "לעולם לא נדע"},
                4, null, new Integer[]{R.drawable.extrasong});

        Question q5 = new Question(
                5,
                "מי מאיתנו שקל לאכול זחל?",
                R.drawable.blurgal,
                new String[]{"גל", "רועי", "ארז", "ברק"},
                1, null, new Integer[]{R.drawable.gal});

        Question q6 = new Question(
                6,
                "מה מהבאים לא עשינו עם האמריקאים בארץ?",
                R.drawable.pic4,
                new String[]{"החלקנו על הקרח", "ראינו מופע דולפינים", "שיחקנו לימבו בפארק", "הלכנו לבאולינג"},
                4, null, null);

        Question q7 = new Question(
                7,
                "מה למדנו שם?",
                R.drawable.what_we_learned,
                new String[]{"פרשת השבוע", "לוח הכפל","מבוא ליהדות ונצרות","לעולם לא נדע"},
                4, null, null);

        Question q8 = new Question(8,
                "מי הפקיר את היד שלו בידי דג מרשע?",
                R.drawable.bluralex,
                new String[]{"אלכס", "איתי", "שלום", "דניאל"},
                1, null,new Integer[]{R.drawable.alexfish});
        Question q9 = new Question(
                9,
                "מה מהבאים עשינו עם האמריקאים בארץ?",
                R.drawable.croped,
                new String[]{"קילפנו אגוזי לוז", "אפינו עוגת שוקולד", "מעכנו ענבים עם הרגליים", "הכנו קובות"},
                4, null, new Integer[]{R.drawable.feetsmash});

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        questions.add(q6);
        questions.add(q7);
        questions.add(q8);
        questions.add(q9);

        return questions;
    }
}
