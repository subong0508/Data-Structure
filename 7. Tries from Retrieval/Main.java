import java.util.*;

/*
* Since this file is not a part of submission,
* you can use any class/packages.
*/

public class Main
{
    public static void main(String[] args)
    {
        // String[] s1 = {"pad", "paddle"};
        // Autocomplete a = new Autocomplete();
        // a.construct(s1);
        // System.out.println(a.autocompletedWord("p"));
        // System.out.println(a.average());
        test8();
    }

    public static void test1(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"yonsei", "young", "yoga", "youtube", "datastructure"};
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"yonsei", "young", "yoga", "youtube", "datastructure", 
                          "yon", "you", "me"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"c", "d", "y", "yo", "yn", "yon", "z"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }

    public static void test2(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"winter", "window", "april"};
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"a", "ap", "april", "win", "window", "winter", "wintery"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"a", "ap", "w", "win", "wd", "wt", "x"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }

    public static void test3(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"flower", "tree", "fly"};
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"flower", "tree", "fly", "flies", "tre", "trea", "trea", "flow"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"f", "fl", "fo", "fy", "t", "r"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }

    public static void test4(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"winter", "window", "win", "widow",
                          "won", "wonder", "wool"};
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"winter", "window", "win", "widow",
                          "won", "wonder", "wool", "apple", "wol", "wi", "wooll"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"w", "wi", "wid", "wiw", "wo", "won", "wd", "wl"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }

    public static void test5(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"peel", "pear"};
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"peer", "peel", "pearl", "pe", "pel", "pearll"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"p", "pa", "pe", "pl", "per"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }

    public static void test6(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"cosmos", "comet", "cosmicbeige", "night" };
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"cosmos", "comet", "cosmicbeige", "night",
                          "ni", "cos", "com"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"c", "co", "com", "cm", "cs", "csi", "cso", "n"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }

    public static void test7(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"paddle", "son", "spiritual", "concerned", "thaw", "island", "sand", "things",
                          "spotless"};
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"paddle", "son", "spiritual", "concerned", "thaw", "island", "sand", "things",
                          "spotless", "spot", "spirit", "concern"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"i", "p", "c", "sa", "so", "sp", "spi", "spo",
                          "t", "ti", "ta"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }

    public static void test8(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"tree", "trea", "treee", "tree"};
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"tree", "trea", "treee", "tr", "tra", "tre"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"t", "tr", "tre", "tree","ta"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }

    public static void test9(){
        System.out.println("***** Testing Trie *****");
        Trie t = new Trie();
        String[] dict1 = {"bear", "bell", "bid", "bull", "bully", "buy", "sell",
                          "stock", "stop"};
        for(String s: dict1){
            System.out.println("Inserting: " + s);
            t.insert(s);
        }

        String[] dict2 = {"bear", "bell", "bid", "bull", "bully", "buy", "sell",
                          "stock", "stop"};
        for(String s: dict2){
            System.out.println(s + " is in t: " + t.exists(s));
        }                    
        System.out.println("t.dictionary(): " + Arrays.toString(t.dictionary()));
        Arrays.sort(dict1);
        System.out.println("dict: " + Arrays.toString(dict1));
        System.out.println();

        System.out.println("***** Testing Autocomplete *****");
        Autocomplete a = new Autocomplete();
        a.construct(dict1);
        String[] words = {"b", "s", "bea", "bi", "bul", "bull", "bully", "st", "sto", "stc", "stp"};
        for(String word: words){
            System.out.println(word + " : " + a.autocompletedWord(word));
        }
        System.out.println();

        System.out.println("average: " + a.average());
    }
}
