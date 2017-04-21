import stdlib.StdAudio;
import stdlib.StdDraw;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        GuitarString[] strings = new GuitarString[37];

        for (int i = 0; i < 37; i++) {
            double note = 440.0 * Math.pow(1.05956, i - 24);
            strings[i] = new GuitarString(note);

        }
        while (true) {

            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) != -1) {
                    int index = keyboard.indexOf(key);
                    strings[index].pluck();
                }
            }

            // compute the superposition of samples
            double sample = 0;
            for (int j = 0; j < 37; j++) {
                sample += strings[j].sample();
            }

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int a = 0; a < 37; a++) {
                strings[a].tic();
            }
        }
    }
}
