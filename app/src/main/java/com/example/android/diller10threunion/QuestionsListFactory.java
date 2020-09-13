package com.example.android.diller10threunion;

import android.net.Uri;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListFactory {

    static final String USER_NAME = "user name";
    static final String TOTAL_QUESTIONS = "total questions";
    static final String CORRECT_ANSWERS = "correct answers";
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageReference;

    QuestionsListFactory(){
        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageReference = mFirebaseStorage.getReference().child("trivia_game");
        StorageReference storageReference = mStorageReference.child("pic8.jpg");

    }

    public List<Question> getQuestions(){

        List<Question> questions = new ArrayList<>();

        Question q1 = new Question(
                1,
                "איפה לא הינו עם האמריקאים בארץ?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic8.jpg?alt=media&token=58c892b3" +
                        "-869c-4d3d-b1ef-34fc671fb366",
                new String[]{"בחוף הים", "בים המלח", "בבית קנדה", "בנגב"},
                2, null,null);

        Question q2 = new Question(
                2,
                "איפה לא הינו עם האמריקאים בארהב?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic1.jpg?alt=media&token=a81b66d7-ef2c-" +
                        "45ce-b2b8-800602efac98",
                new String[]{"האקווריום הלאומי", "בית כנסת יהודי", "מופע דולפינים", "ברודוואי"},
                4, null, null);

        Question q3 = new Question(
                3,
                "מיד אחרי איזו פעילות אכלנו את הגלידה הזאת?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fice_cream.jpg?alt=media&token=d6cb6130-e0b0" +
                        "-4b84-b22c-ecbd443b2f20",
                new String[]{"סיור במוזיאון בבולטימור", "סיור באקווריום הלאומי", "שופינג בטיימס סקוור",
                        "התנדבות בבית אבות"},
                2, null, null);

        Question q4 = new Question(
                4,
                "איזה שיר שרנו בתמונה?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fsong.jpg?alt=media&token=92ff5af7-5996" +
                        "-4717-93a9-b5c0fb55a264",
                new String[]{"כד קטן", "תנו לשמש לעלות", "הבאנו שלום עליכם", "לעולם לא נדע"},
                4, null, new Integer[]{R.drawable.extrasong});

        Question q5 = new Question(
                5,
                "מי מאיתנו שקל לאכול זחל?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fblurgal.jpeg?alt=media&token=23728186-e2ca" +
                        "-48a7-8f71-fb79ba93fa12",
                new String[]{"גל", "רועי", "ארז", "ברק"},
                1, null, new Integer[]{R.drawable.gal});

        Question q6 = new Question(
                6,
                "מה מהבאים לא עשינו עם האמריקאים בארץ?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic4.jpg?alt=media&token=29969817-42eb" +
                        "-40b9-9396-fe6525f14bf0",
                new String[]{"החלקנו על הקרח", "יצאנו לבר על החוף", "שיחקנו לימבו בפארק",
                        "הלכנו לבאולינג"},
                4, null, new Integer[]{R.drawable.limboeilon});

        Question q7 = new Question(
                7,
                "מה למדנו שם?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fwhat_we_learned.jpg?alt=media&token=0c56443d" +
                        "-b85d-4145-a2cf-8f419e7987de",
                new String[]{"פרשת השבוע", "לוח הכפל","מבוא ליהדות ונצרות","לעולם לא נדע"},
                4, null, null);

        Question q8 = new Question(
                8,
                "מי ניסתה לגזום לג'ולייט את השיער?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fshaniblur.jpeg?alt=media&token=024ff5bb-2053" +
                        "-40d8-bc0d-7e747748a1ea",
                new String[]{"עדיה", "אילון", "שני", "ליהי"},
                3, null, new Integer[]{R.drawable.farmshani});

        Question q9 = new Question(
                9,
                "מה מהבאים עשינו עם האמריקאים בארץ?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fcroped.jpg?alt=media&token=9e00566d-d9c4-4287" +
                        "-b84d-6753d6b50293",
                new String[]{"חלבנו פרות", "קטפנו תותים מהשדה", "מעכנו ענבים עם הרגליים",
                        "הכנו קובות"},
                3, null, new Integer[]{R.drawable.feet});

        Question q10 = new Question(
                10,
                "תראו מי רוצה לשאול אתכם שאלה",
                null,
                new String[]{"מלכה", "פנינה", "ליאל", "אלכס"},
                4, "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                ".appspot.com/o/videos%2Fjoe.mp4?alt=media&token=43b4ee84-f5fd-447d" +
                "-a048-b0824afb7ad4", null);

        Question q11 = new Question(
                11,
                "מי נכנס/ה בניו-יורק לחנות צילום?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fgirls.jpg?alt=media&token=ae6925e1-1f95" +
                        "-49a4-96a6-41780f1f701e",
                new String[]{"ליבי", "לנה", "שלומית", "שלום"},
                2, null, new Integer[]{R.drawable.lena});

        Question q12 = new Question(
                12,
                "כמה זוגות בסתר היו לנו בקבוצה, שמעולם לא שמעת עליהם?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Ferezandjulia.jpg?alt=media&token=d12d0675" +
                        "-1758-40b1-9163-67de14a5099a",
                new String[]{"בדיוק 1", "יותר מ-5", "יותר מ-7", "פחות מ-15"},
                2, null, new Integer[]{R.drawable.fish2update});

        Question q13 = new Question(
                13,
                "תראו מי רוצה לשאול אתכם שאלה",
                null,
                new String[]{"Saint patrick´s day", "Easter", "Birth of the Bab","Good Friday"},
                1, "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                ".appspot.com/o/videos%2Fjulia.mp4?alt=media&token=f072ab10-4f80-4c1e-b1cd" +
                "-8d01384183da", null);

        Question q14 = new Question(
                14,
                "תראו מי רוצה לשאול אתכם שאלה",
                null,
                new String[]{"7", "4", "5","10"},
                1, "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                ".appspot.com/o/videos%2FLaura.mp4?alt=media&token=34afc244-fc8b-45b7-8ef0" +
                "-fe5612115ea3", null);

        Question q15 = new Question(
                15,
                "כמה זוגות היו לנו בקבוצה?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic13.jpg?alt=media&token=69fa53e3-0061" +
                        "-4c4d-a3fc-5b779c598b6a",
                new String[]{"12,576", "20", "9", "לעולם לא נדע"},
                4, null, new Integer[]{R.drawable.look});

        Question q16 = new Question(
                16,
                "תראו מי רוצה לשאול אתכם שאלה",
                null,
                new String[]{"אני רעב. בואו נלך לאכול", "אתה מריח רע. שים דאודורנט",
                        "How you doin? Joe Tribbiani style","מה הסיכוי שאתה קונה לי צב?"},
                3, "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                ".appspot.com/o/videos%2FCody%20-%20Copy.mp4?alt=media&token=15bd4eea-0a89-486a" +
                "-a0bf-e2a395aa0d22", new Integer[]{R.drawable.tzav});

        Question q17 = new Question(
                17,
                "מה לא היה בלילה של מסיבת המרתף בבולטימור?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Ffaces.jpg?alt=media&token=be961d99-4af9" +
                        "-4e9d-b70e-767b0b60a800",
                new String[]{"כאלה שהתגופפו בארון בגדים", "כאלה שחיממו זה את זה בשירותים",
                        "כאלה ששיחקו שחמט", "כאלה שרקדו לצלילי המוזיקה"},
                3, null, null);

        Question q18 = new Question(
                18,
                "מה מהבאים לא עשינו עם האמריקאים בארץ?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fdesert.jpg?alt=media&token=abc98406-8901" +
                        "-45d6-b38c-ef3150772ebd",
                new String[]{"צבענו תמונת ענק בקנבאס", "מיני טירונות במדים", "יוגה בבוקר על החוף",
                        "יצאנו לראות סרט"},
                4, null, new Integer[]{R.drawable.military});

        Question q19 = new Question(
                19,
                "תראו מי רוצה לשאול אתכם שאלה",
                null,
                new String[]{"שחינו בנחל הקיבוצים", "ביקרנו ביד לבנים",
                        "עשינו קטיף ענבים","טיילנו בעין עבדת"},
                1, "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                ".appspot.com/o/videos%2FAustin%20-%20Copy.mp4?alt=media&token=dfef510d-9108-41ed-9261" +
                "-4105403adc78", null);

        Question q20 = new Question(
                20,
                "מה נתנו לנו לאכול באוטובוס כשרק הגענו לארהב?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic12.jpg?alt=media&token=ace9088f-91a9" +
                        "-47d3-907a-59bdf9b3a072",
                new String[]{"פופ טארטס עם מילקשייק", "דונאט עם סוכריות צבעוניות",
                        "בייגל עם חמאת בוטנים וריבה", "שאוורמה בלאפה"},
                3, null, new Integer[]{R.drawable.proofupdated});

        Question q21 = new Question(
                21,
                "מה הפרס שמצאנו כשפתרנו את כל החידות בפעילות ביער המלאכים?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic17.jpg?alt=media&token=439b6166-8e9b-41f0" +
                        "-a86c-8160d4d6922d",
                new String[]{"ארגז של קרמבו", "ארגז של גלידות",
                        "טארט טאטן", "ארגז עם שאוורמות בלאפות"},
                1, null, null);

        Question q22 = new Question(
                22,
                "כמה שאטים של טקילה ברק צריך עד שהוא מתחיל לדבר לבקבוק?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fice.jpg?alt=media&token=db86f777-6921-4a61" +
                        "-aa6f-b025b2487d7c",
                new String[]{"50", "8",
                        "5", "0"},
                2, null, new Integer[]{R.drawable.barak});

        Question q23 = new Question(
                23,
                "מה נתנו לנו לאכול כל בוקר ב-JCC?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic2.jpg?alt=media&token=abfe7792-4045" +
                        "-4ad0-affd-04b0aedfba2d",
                new String[]{"רד ולווט קאפקייקס", "פיצה פפרוני",
                        "דונאטים", "מקדונלדס - רק סלטים"},
                3, null, null);

        Question q24 = new Question(
                24,
                "מה מהבאים עשינו עם האמריקים בחול?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic24.jpg?alt=media&token=0f1a3865-4ede" +
                        "-4bb9-8cc0-e3f5cc72506c",
                new String[]{"שיגועים בדיסנילנד",
                        "רואוד טריפ למקסיקו","בנג'י מהגרנד קניון","מסיבת פיג'מות באולם ספורט"},
                4, null, new Integer[]{R.drawable.laurapillow});

        Question q25 = new Question(
                25,
                "מי מאיתנו פתח חזית התקפה כנגד דג מרשע?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fitaiblur.jpeg?alt=media&token=d69c28de-ded8" +
                        "-447c-9ceb-1990ed0066b5",
                new String[]{"איתי", "דניאל", "עידו", "ברק"},
                1, null, new Integer[]{R.drawable.itaifish});

        Question q26 = new Question(
                26,
                "כמה נהנתם מהמשחק מ-1 עד 10?",
                "https://firebasestorage.googleapis.com/v0/b/diller10threunion" +
                        ".appspot.com/o/trivia_game%2Fpic17.jpg?alt=media&token=439b6166-8e9b" +
                        "-41f0-a86c-8160d4d6922d",
                new String[]{"12", "100",
                        "1,000", "נשארתי פעור פה, לא ניתן לכימות"},
                4, null, null);

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5); //Gal
        questions.add(q13); //Julia
        questions.add(q6);
        questions.add(q11);
        questions.add(q8); //Shani
        questions.add(q12); //Secret
        questions.add(q10); //Joe
        questions.add(q15); // Couples
        questions.add(q7);
        questions.add(q14); //Laura
        questions.add(q17);
        questions.add(q18);
        questions.add(q19); //Austin
        questions.add(q23);
        questions.add(q9);
        questions.add(q25); //Itai
        questions.add(q16); // Cody
        questions.add(q20);
        questions.add(q21);
        questions.add(q22); //Barak
        questions.add(q24);
        questions.add(q26);
        return questions;
    }
}
