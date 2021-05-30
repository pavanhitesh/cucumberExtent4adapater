package com.generalactions;

import java.util.*;
import java.util.stream.Collectors;

public class StringManipulations {

    /**
     * To Reversal String only Alphabets by ignoring others characters
     * @param data
     */
    public String stringReversalOnlyAlphabets(String data){
        char  [] characters = data.toCharArray();
        int i = 0;
        int j = characters.length-1;
        while(i<j){
            if(!String.valueOf(characters[i]).matches("[a-zA-Z0-9]")){
                i++;
            }else if(!String.valueOf(characters[j]).matches("[a-zA-Z0-9]")){
                j--;
            }else{
                char temp = characters[i];
                characters[i] = characters[j];
                characters[j] =temp;
                i++;
                j--;
            }
        }
        return new String(characters);
    }

    public void calculateTypeOfCharacters(String string){
        char [] chars = string.toCharArray();
        int alphabetsCount = 0;
        int numericCount =0 ;
        int specialCount = 0;
        int spacesCount = 0;
        for(int i=0;i<chars.length;i++) {
            if (String.valueOf(chars[i]).matches("[0-9]"))
                numericCount++;
            else if(String.valueOf(chars[i]).matches("[a-zA-Z]"))
                alphabetsCount++;
            else if(String.valueOf(chars[i]).matches("[\\s]"))
                spacesCount++;
            else
                specialCount++;
        }
        System.out.println(String.format("Alphabets Count [%s]  Numerics Count [%s] Spaces Count [%s] SpecialCount [%s]",alphabetsCount,numericCount,spacesCount,specialCount));
    }

    public void calculateDuplicatesInString(String string){
        char [] chars = string.toLowerCase(Locale.ROOT).toCharArray();
        Map<Character,Integer> charMap = new HashMap<>();
        for(char cha:chars){
            if(charMap.containsKey(cha)){
                charMap.put(cha,charMap.get(cha)+1);
            }else{
                charMap.put(cha,1);
            }
        }
        System.out.println(charMap.toString());
        Map<Character,Integer> duplicateMap = charMap.entrySet().stream().filter(map -> map.getValue()>1).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        System.out.println("Duplicates"+duplicateMap.toString());
    }

    public boolean isStringsAnagramUsingArray(String string1,String string2){
        if(string1.length()!=string2.length()){
            return  false;
        }
        int [] a = new int[256];
        for(int i=0;i<string1.length();i++){
            int index1 = (int)string1.charAt(i);
            int index2 = (int)string2.charAt(i);
            a[index1]++;
            a[index2]--;
        }
        for(int j=0;j<a.length;j++){
            if(a[j] != 0)
                return false;
        }
        return true;
    }

public void printUniqueWordsInString(String string){
     HashMap<String,Integer> wordsMap = new HashMap<>();
     String [] words = string.split("\\W");
     for(String word:words){
         if(wordsMap.containsKey(word))
             wordsMap.put(word,wordsMap.get(word)+1);
         else
             wordsMap.put(word,1);
     }
     List<String> uniqueWords = wordsMap.entrySet().stream().filter(map-> map.getValue()==1).map(Map.Entry::getKey).collect(Collectors.toList());
     System.out.println(uniqueWords);
}

public boolean isStringPalindrome(String string){
        int i =0 ;
        int j = string.length()-1;
        while(i<j) {
            if(string.charAt(i) != string.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
}

public void calculateListOfPossiblePalindromeInString(String string){
        int count =0 ;
        for(int i=0;i<string.length();i++){
            for(int j=i+1;j<=string.length();j++){
                String subString = string.substring(i,j);
                boolean isPalindrome = isStringPalindrome(subString);
                if(isPalindrome)
                    count++;
                System.out.println(String.format("Substring - [%s] is Palindrome or not [%s]",subString,isPalindrome));
            }
        }
        System.out.println(String.format("No of possible palindrome are [%s]",count));
}

public void findFirstNonRepeatingCharacterInString(String string){
        LinkedHashMap<Character,Integer> list = new LinkedHashMap<>();
        char [] chars = string.toCharArray();
        for(char cha:chars){
            if(list.containsKey(cha)){
                list.put(cha,list.get(cha)+1);
            }else{
                list.put(cha,1);
            }
        }
        System.out.println(String.format("The first non repeating char is [%s]",list.entrySet().stream().filter(map -> map.getValue()==1).map(Map.Entry::getKey).findFirst().get().toString() ));
}

public void convertStringToInteger(String string)  {
        try {
            if (string != null) {
                int num = 0;
                for (int i = 0; i < string.length(); i++) {
                    num = num * 10 + ((int) string.charAt(i) - '0');
                }
                System.out.println(num);
            } else {
                throw new Exception("Can not convert null to integer");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

}

    public static void main(String[] args) {
        StringManipulations rev = new StringManipulations();
        String string = "#STRING 123 & Test%";
        System.out.println(string);
        System.out.println(rev.stringReversalOnlyAlphabets(string));
        rev.calculateTypeOfCharacters(string);
        rev.calculateDuplicatesInString(string);
        System.out.println("Anagram "+rev.isStringsAnagramUsingArray("hITESH","TESHIh"));
        rev.printUniqueWordsInString("Java is great.Python is also great.");
        System.out.println("String is Palindrome "+rev.isStringPalindrome("abba"));
        rev.calculateListOfPossiblePalindromeInString("MALAYALAM");
        rev.findFirstNonRepeatingCharacterInString("ppython");
        rev.convertStringToInteger("1");
    }

}


