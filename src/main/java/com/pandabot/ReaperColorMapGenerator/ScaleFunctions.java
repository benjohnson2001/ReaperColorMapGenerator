package com.pandabot.ReaperColorMapGenerator;

import java.util.ArrayList;
import java.util.List;

public class ScaleFunctions {

   private static List<Scale> getScales() {

      List<Scale> scales = new ArrayList<>();
      scales.add(new Scale("Major", "101011010101"));
      scales.add(new Scale("NaturalMinor", "101101011010"));
      scales.add(new Scale("HarmonicMinor", "101101011001"));
      scales.add(new Scale("MelodicMinor", "101101010101"));
      scales.add(new Scale("Pentatonic", "101010010100"));
      scales.add(new Scale("Ionian", "101011010101"));
      scales.add(new Scale("Aeolian", "101101011010"));
      scales.add(new Scale("Dorian", "101101010110"));
      scales.add(new Scale("Mixolydian", "101011010110"));
      scales.add(new Scale("Phrygian", "110101011010"));
      scales.add(new Scale("Lydian", "101010110101"));
      scales.add(new Scale("Locrian", "110101101010"));

      return scales;
   }

   private static List<String> getNoteNames() {

      List<String> noteNames = new ArrayList<>();
      noteNames.add("c");
      noteNames.add("cSharp");
      noteNames.add("d");
      noteNames.add("dSharp");
      noteNames.add("e");
      noteNames.add("f");
      noteNames.add("fSharp");
      noteNames.add("g");
      noteNames.add("gSharp");
      noteNames.add("a");
      noteNames.add("aSharp");
      noteNames.add("b");

      return noteNames;
   }

   public static boolean[] getScalePattern(int scaleTonicNote, int scaleType) {

      List<Scale> scales = getScales();

      String scalePatternString = scales.get(scaleType).getPattern();

      boolean[] scalePattern = {false, false, false, false, false, false, false, false, false, false, false, false};

      for (int i = 0; i < scalePatternString.length(); i++) {

         int note = Util.getNotesIndex(scaleTonicNote + i);

         if (scalePatternString.substring(i, i+1).equals("1")) {
            scalePattern[note] = true;
         } else {
            scalePattern[note] = false;
         }
      }

      return scalePattern;
   }

   public static String getColorMapFileName(int scaleTonicNote, int scaleType) {

      List<Scale> scales = getScales();
      List<String> noteNames = getNoteNames();

      return noteNames.get(scaleTonicNote) + scales.get(scaleType).getName() + ".png";
   }

   public static int getNumberOfScales() {
      List<Scale> scales = getScales();
      return scales.size();
   }
}
