package com.pandabot.ReaperColorMapGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Run {

   private static void generateColorMapImage(int scaleTonicNote, int scaleType) throws IOException {

      int width = 157;
      int height = 130;

      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D graphics2D = bufferedImage.createGraphics();

      boolean[] scalePattern = ScaleFunctions.getScalePattern(scaleTonicNote, scaleType);

      int scaleNote = 1;
      for (int i = scaleTonicNote; i < scaleTonicNote + 12; i++) {

         int noteIndex = Util.getNotesIndex(i);

         if (scalePattern[noteIndex]) {

            switch (scaleNote) {

               case 1:
                  graphics2D.setPaint(NoteColors.tonicNote);
                  break;
               case 4:
                  graphics2D.setPaint(NoteColors.fourthScaleNote);
                  break;
               case 7:
                  graphics2D.setPaint(NoteColors.leadingNote);
                  break;
               default:
                  graphics2D.setPaint(NoteColors.regularNote);
            }
            scaleNote++;
         } else {
            graphics2D.setPaint(NoteColors.nonScaleNote);
         }

         graphics2D.drawLine(noteIndex, 0, noteIndex, 63);
      }

      scaleNote = 1;
      for (int i = scaleTonicNote; i < scaleTonicNote + 12; i++) {

         int noteIndex = Util.getNotesIndex(i);

         if (scalePattern[noteIndex]) {

            switch (scaleNote) {

               case 1:
                  graphics2D.setPaint(NoteColors.selectedTonicNote);
                  break;
               case 4:
                  graphics2D.setPaint(NoteColors.selectedFourthScaleNote);
                  break;
               case 7:
                  graphics2D.setPaint(NoteColors.selectedLeadingNote);
                  break;
               default:
                  graphics2D.setPaint(NoteColors.selectedRegularNote);
            }
            scaleNote++;
         } else {
            graphics2D.setPaint(NoteColors.selectedNonScaleNote);
         }

         graphics2D.drawLine(noteIndex, 65, noteIndex, 128);
      }

      //graphics2D.setPaint(NoteColors.regularOutline);
      //graphics2D.drawLine(0, height/2-1, width-1, height/2-1);
      //graphics2D.setPaint(NoteColors.selectedOutline);
      //graphics2D.drawLine(0, height-1, width-1, height-1);

      graphics2D.setPaint(NoteColors.nonScaleNote);
      graphics2D.fillRect(12, 0, width - 12, height/2-1);

      graphics2D.setPaint(NoteColors.selectedNonScaleNote);
      graphics2D.fillRect(12, height/2, width - 12, height/2-1);


      int numberOfPitches = 12;
      int numberOfMidiNotes = 128;

      // adding blue color to indicate breath notes
      graphics2D.setPaint(NoteColors.breathNote);
      graphics2D.drawLine(numberOfPitches + numberOfMidiNotes, 0, numberOfPitches + numberOfMidiNotes, 63);

      // adding light red color to indicate low grit notes
      int lowGritVelocity = 125;
      graphics2D.setPaint(NoteColors.lowGritNote);
      graphics2D.drawLine(numberOfPitches + lowGritVelocity, 0, numberOfPitches + lowGritVelocity, 63);

      // adding medium red color to indicate medium grit notes
      int mediumGritVelocity = 126;
      graphics2D.setPaint(NoteColors.mediumGritNote);
      graphics2D.drawLine(numberOfPitches + mediumGritVelocity, 0, numberOfPitches + mediumGritVelocity, 63);

      // adding bright red color to indicate high grit notes
      int highGritVelocity = 127;
      graphics2D.setPaint(NoteColors.highGritNote);
      graphics2D.drawLine(numberOfPitches + highGritVelocity, 0, numberOfPitches + highGritVelocity, 63);

      String colorMapFileName = ScaleFunctions.getColorMapFileName(scaleTonicNote, scaleType);
      ImageIO.write(bufferedImage, "PNG", new File("./output/" + colorMapFileName));
   }

   public static void main(String[] args) throws Exception {

      int numberOfScales = ScaleFunctions.getNumberOfScales();
      for (int scaleType = 0; scaleType < numberOfScales; scaleType++) {
         for (int scaleTonicNote = 0; scaleTonicNote < 12; scaleTonicNote++) {
            generateColorMapImage(scaleTonicNote, scaleType);
         }
      }
   }
}
