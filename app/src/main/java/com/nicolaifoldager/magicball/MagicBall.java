package com.nicolaifoldager.magicball;

//Import libraries
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class MagicBall extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        //Call the layout from the activity_magic_ball.xml in /layouts
        setContentView(R.layout.activity_magic_ball);

        //Declare answerLabel and Star as a TextView & ImageView variable and cross it with the layout xml file
        final TextView answerLabel = (TextView) findViewById(R.id.advice);
        final ImageView fog = (ImageView) findViewById(R.id.fog);

        //Declare the stars
        final ImageView randStar1 = (ImageView) findViewById(R.id.randStar1);
        final ImageView randStar2 = (ImageView) findViewById(R.id.randStar2);
        final ImageView randStar3 = (ImageView) findViewById(R.id.randStar3);
        final ImageView randStar4 = (ImageView) findViewById(R.id.randStar4);

        //Declare startup to remove stars and run the animation
        final Animation startup = new AlphaAnimation(0.0f, 0.0f);

        //Set the duration of the startup animation and set it to fill after animation has completed
        startup.setDuration(1);
        startup.setFillAfter(true);
        fog.startAnimation(startup);

        //Declare the classes AlphaAnimation to make the text fade textIn/textOut & declare the duration
        final Animation textIn = new AlphaAnimation(0.0f, 1.0f);
        textIn.setDuration(3000);
        final Animation textOut = new AlphaAnimation(1.0f, 0.0f);
        textOut.setDuration(3000);

        //Declare the classes AlphaAnimation to make the text fade fogIn/fogOut & declare the duration
        final Animation fogIn = new AlphaAnimation(0.0f, 1.0f);
        fogIn.setDuration(3000);
        final Animation fogOut = new AlphaAnimation(1.0f, 0.0f);
        fogOut.setDuration(2000);
        fogOut.setFillAfter(true);

        //Random star animations - randStar1
        final Animation randStar1In = new AlphaAnimation(0.0f, 1.0f);
        randStar1In.setDuration(4000);
        randStar1In.setFillAfter(true);
        final Animation randStar1Out = new AlphaAnimation(1.0f, 0.0f);
        randStar1Out.setStartOffset(4000);
        randStar1Out.setDuration(3000);
        randStar1Out.setFillAfter(true);

        //Random star animations - randStar2
        final Animation randStar2In = new AlphaAnimation(0.0f, 1.0f);
        randStar2In.setStartOffset(500);
        randStar2In.setDuration(2000);
        randStar2In.setFillAfter(true);
        final Animation randStar2Out = new AlphaAnimation(1.0f, 0.0f);
        randStar2Out.setStartOffset(2500);
        randStar2Out.setDuration(2500);
        randStar2Out.setFillAfter(true);

        //Random star animations - randStar3
        final Animation randStar3In = new AlphaAnimation(0.0f, 1.0f);
        randStar3In.setDuration(3000);
        randStar3In.setFillAfter(true);
        final Animation randStar3Out = new AlphaAnimation(1.0f, 0.0f);
        randStar3Out.setStartOffset(3000);
        randStar3Out.setDuration(3000);
        randStar3Out.setFillAfter(true);

        //Random star animations - randStar4
        final Animation randStar4In = new AlphaAnimation(0.0f, 1.0f);
        randStar4In.setStartOffset(500);
        randStar4In.setDuration(1500);
        randStar4In.setFillAfter(true);
        final Animation randStar4Out = new AlphaAnimation(1.0f, 0.0f);
        randStar4Out.setStartOffset(1500);
        randStar4Out.setDuration(2500);
        randStar4Out.setFillAfter(true);

        //Possible answers in an array called answers
        final String[] answers = {"Don't count on it", "Maybe tomorrow", "Try again later", "I doubt it", "It is certain",
                                "It is decidedly so", "Yes definitely", "You may rely on it", "Most likely", "As I see it, yes",
                                "Outlook good", "Yes", "Signs point to yes", "Reply hazy, try again", "Better not tell you now",
                                "Cannot predict now", "Focus and ask again", "My reply is no", "My sources say no", "Very doubtful"};

        //----------------------------------------------------------------------------------------//

         //Start a animation listener for startup.animation
         startup.setAnimationListener(new Animation.AnimationListener() {

             //When startup.animation has ended run the following code:
             @Override
             public void onAnimationEnd(Animation animation) {
                 randStar1.startAnimation(randStar1In);
                 randStar2.startAnimation(randStar2In);
                 randStar3.startAnimation(randStar3In);
                 randStar4.startAnimation(randStar4In);
             }

             //Unused
             @Override
             public void onAnimationStart(Animation animation) {}
             @Override
             public void onAnimationRepeat(Animation animation) {}
         });

        //----------------------------------------------------------------------------------------//

        //Start the setOnClickListener for the getAnswerButton aka. button1
        fog.setOnClickListener(new View.OnClickListener() {
            @Override

            //Start the function onClick, this called by activated by onClickListener
            public void onClick(View v) {

                //Debug fun to see when the button has been pressed
                Log.i("Debug", "ball pressed");

                //Perform haptic feedback on click. Using haptic feedback to avoid asking for vibrate permission.
                fog.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

                //The Button was clicked, so pick a number from the array answers
                int randAnswer = (int) (Math.random() * 20);

                //Random integer to rotate around
                int randRotate = randAnswer * 7;

                //Rotate right fog
                //fog.setRotation(randRotate * 7);

                //Fade the text out and the fog in
                answerLabel.startAnimation(textOut);
                fog.startAnimation(fogIn);
            }
        });

        //Start an animation listener for starIn
        fogIn.setAnimationListener(new Animation.AnimationListener() {

            //When the fogIn animation stops trigger the following
            @Override
            public void onAnimationEnd(Animation animation) {


                //The Button was clicked, so pick a number from the array answers
                int randAnswer = (int) (Math.random() * 20);

                //Update answerLabel aka. textView
                answerLabel.setText(answers[randAnswer]);

                //Delay advice text half a second for flair
                textIn.setStartOffset(400);

                //Set textIn.animation to fill after the animation is complete
                textIn.setFillAfter(true);

                //Fade text in and fog out
                answerLabel.startAnimation(textIn);
                fog.startAnimation(fogOut);

                //Log entry, 1 round
                Log.i("Debug", "1 round done");
            }

            //Not used - Triggers on animation start
            @Override
            public void onAnimationStart(Animation animation) {}
            //Not used - Triggers on animation repeat
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        //----------------------------------------------------------------------------------------//

        randStar1In.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                randStar1.startAnimation(randStar1Out);
            }

            //Not used - Triggers on animation start
            @Override
            public void onAnimationStart(Animation animation) {}
            //Not used - Triggers on animation repeat
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        randStar1Out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                randStar1.startAnimation(randStar1In);
            }

            //Not used - Triggers on animation start
            @Override
            public void onAnimationStart(Animation animation) {}
            //Not used - Triggers on animation repeat
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        //----------------------------------------------------------------------------------------//

        randStar2In.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                randStar2.startAnimation(randStar2Out);
            }

            //Not used - Triggers on animation start
            @Override
            public void onAnimationStart(Animation animation) {}
            //Not used - Triggers on animation repeat
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        randStar2Out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                randStar2.startAnimation(randStar2In);
            }

            //Not used - Triggers on animation start
            @Override
            public void onAnimationStart(Animation animation) {}
            //Not used - Triggers on animation repeat
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

         //----------------------------------------------------------------------------------------//

         randStar3In.setAnimationListener(new Animation.AnimationListener() {

             @Override
             public void onAnimationEnd(Animation animation) {
                 randStar3.startAnimation(randStar3Out);
             }

             //Not used - Triggers on animation start
             @Override
             public void onAnimationStart(Animation animation) {}
             //Not used - Triggers on animation repeat
             @Override
             public void onAnimationRepeat(Animation animation) {}
         });

         randStar3Out.setAnimationListener(new Animation.AnimationListener() {

             @Override
             public void onAnimationEnd(Animation animation) {
                 randStar3.startAnimation(randStar3In);
             }

             //Not used - Triggers on animation start
             @Override
             public void onAnimationStart(Animation animation) {}
             //Not used - Triggers on animation repeat
             @Override
             public void onAnimationRepeat(Animation animation) {}
         });

         //----------------------------------------------------------------------------------------//

         randStar4In.setAnimationListener(new Animation.AnimationListener() {

             @Override
             public void onAnimationEnd(Animation animation) {
                 randStar4.startAnimation(randStar4Out);
             }

             //Not used - Triggers on animation start
             @Override
             public void onAnimationStart(Animation animation) {}
             //Not used - Triggers on animation repeat
             @Override
             public void onAnimationRepeat(Animation animation) {}
         });

         randStar4Out.setAnimationListener(new Animation.AnimationListener() {

             @Override
             public void onAnimationEnd(Animation animation) {
                 randStar4.startAnimation(randStar4In);
             }

             //Not used - Triggers on animation start
             @Override
             public void onAnimationStart(Animation animation) {}
             //Not used - Triggers on animation repeat
             @Override
             public void onAnimationRepeat(Animation animation) {}
         });

    }

}