package com.pandabot.ReaperColorMapGenerator;

public class Scale {

   private String name;
   private String pattern;

   public Scale(String name, String pattern) {
      this.name = name;
      this.pattern = pattern;
   }

   public String getName() {
      return name;
   }

   public String getPattern() {
      return pattern;
   }
}
