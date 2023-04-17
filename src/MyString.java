import java.util.*;

public class MyString {

    private List<Character> chars = new ArrayList<>();

    public MyString(char[] chars){
        for(char c : chars){
            this.chars.add(c);
        }
    }
    public MyString(){ }
    public MyString(char c){ chars.add(c); }

    /**
     *
     * @param index
     * @return Returns the Character at the specified position
     */
    public char charAt(int index){
        if(index >= chars.size()) throw new IndexOutOfBoundsException();
        return chars.get(index);
    }

    /**
     *
     * @param anotherString
     * @return Return -1 if this String is smaller, 1 if this String is bigger, 0 if they are the same
     */
    public int compareTo(MyString anotherString){
        int i = 0, j = 0;
        while(i < chars.size() && j < anotherString.length()){
            if(chars.get(i) < anotherString.chars.get(j)) return -1;
            else if(chars.get(i) > anotherString.chars.get(j)) return 1;
            i++;
            j++;
        }
        if(i < chars.size()) return 1;
        else if(j < anotherString.chars.size()) return -1;
        else return 0;
    }

    /**
     *
     * @param anotherString
     * @return Compares two strings lexicographically, ignoring case differences.
     */
    public int compareToIgnoreCase(MyString anotherString){
        MyString s = this.toLowerCase();
        MyString t = anotherString.toLowerCase();
        return s.compareTo(t);
    }

    /**
     *
     * @param str
     * @return Concatenates the specified string to the end of this string.
     */
    public MyString concat(MyString str){
        this.chars.addAll(str.chars);
        return this;
    }

    /**
     *
     * @param str String
     * @return Returns true if and only if this string contains the specified sequence of char values.
     */
    public boolean contains(MyString str){
        if(str == null || str.length() == 0) return false;
        for(int i = 0; i < this.length(); i++){
            char c = chars.get(i);
            if(c == str.chars.get(0)){
                boolean yes = true;
                if(i + str.length() - 1 >= this.length()) break;
                for(int j = 0; j < str.length(); j++){
                    if(chars.get(i + j) != str.chars.get(j)){
                        yes = false;
                        break;
                    }
                }
                if(yes) return true;
            }
        }
        return false;
    }

    /**
     *
     * @param c CharSequence
     * @return Returns true if and only if this string contains the specified sequence of char values.
     */
    public boolean contains(char... c){
        return contains(new MyString(c));
    }

    /**
     *
     * @param c CharSequence
     * @return Compares this string to the specified CharSequence
     */
    public boolean contentEquals(char... c){
        if(length() != c.length) return false;
        for(int i = 0; i < c.length; i++){
            if(chars.get(i) != c[i]) return false;
        }
        return true;
    }

    /**
     *
     * @param data
     * @return Returns a String that represents the character sequence in the array specified.
     */
    public static MyString copyValueOf(char[] data){
        return new MyString(data);
    }

    /**
     *
     * @param suffix
     * @return Tests if this string ends with the specified suffix.
     */
    public boolean endsWith(MyString suffix){
        if(suffix.length() > length()) return false;
        int i = length() - 1, j = suffix.length() - 1;
        while(j >= 0){
            if(chars.get(i--) != suffix.chars.get(j--)) return false;
        }
        return true;
    }

    /**
     *
     * @param ob
     * @return Compares this string to the specified object.
     */
    public boolean equals(Object ob){
        if(ob.getClass() != this.getClass()) return false;
        return this.contentEquals(toCharArray((MyString) ob));
    }

    /**
     *
     * @param str
     * @return Compares this String to another String, ignoring case considerations.
     */
    public boolean equalsIgnoreCase(MyString str){
        return this.toLowerCase().equals(str.toLowerCase());
    }

    /**
     *
     * @param c
     * @return Returns the index within this string of the first occurrence of the specified character.
     */
    public int indexOf(int c){
        for(int i = 0; i < length(); i++){
            if(charAt(i) == c) return i;
        }
        return -1;
    }

    /**
     *
     * @param c
     * @param fromIndex
     * @return Returns the index within this string of the first occurrence of the specified character, starting the search at the specified index.
     */
    public int indexOf(char c, int fromIndex){
        for(int i = fromIndex; i < length(); i++){
            if(charAt(i) == c) return i;
        }
        return -1;
    }

    /**
     *
     * @param str
     * @return Returns the index within this string of the first occurrence of the specified substring.
     */
    public int indexOf(MyString str){
        return indexOfHelper(str, 0);
    }

    /**
     *
     * @param str
     * @param from
     * @return Returns the index within this string of the first occurrence of the specified substring, starting at the specified index.
     */
    public int indexOf(MyString str, int from){
        return indexOfHelper(str, from);
    }

    /**
     *
     * @return Returns true if, and only if, length() is 0.
     */
    public boolean isEmpty(){
        return length() == 0;
    }

    /**
     *
     * @param c
     * @return Returns the index within this string of the last occurrence of the specified character.
     */
    public int lastIndexOf(char c){
        for(int i = length() - 1; i >= 0; i--){
            if(charAt(i) == c) return i;
        }
        return -1;
    }

    /**
     *
     * @param c
     * @param from
     * @return Returns the index within this string of the last occurrence of the specified character, searching backward starting at the specified index.
     */
    public int lastIndexOf(int c, int from){
        for(int i = length() - 1; i >= from; i--){
            if(charAt(i) == c) return i;
        }
        return -1;
    }

    /**
     *
     * @param str
     * @return Returns the index within this string of the last occurrence of the specified substring.
     */
    public int lastIndexOf(MyString str){
        return lastIndexOfHelper(str, 0);
    }

    /**
     *
     * @param str
     * @param from
     * @return Returns the index within this string of the last occurrence of the specified substring, searching backward starting at the specified index.
     */
    public int lastIndexOf(MyString str, int from){
        return lastIndexOfHelper(str, from);
    }

    /**
     *
     * @return Returns the length of this String
     */
    public int length(){
        return chars.size();
    }

    /**
     *
     * @param oldChar
     * @param newChar
     * @return Returns a new string resulting from replacing all occurrences of oldChar in this string with newChar.
     */
    public MyString replace(char oldChar, char newChar){
        for(int i = 0; i < length(); i++){
            if(charAt(i) == oldChar) chars.set(i, newChar);
        }
        return this;
    }

    /**
     *
     * @param target
     * @param replacement
     * @return Replaces each substring of this string that matches the literal target sequence with the specified literal replacement sequence.
     */
    public MyString replace(char[] target, char[] replacement){
        if(target.length != replacement.length) return this;
        for(int i = 0; i < length(); i++){
            if(charAt(i) == target[0]){
                boolean yes = true;
                if(i + target.length - 1 >= length()) break;
                for(int j = 0; j < target.length; j++){
                    if(charAt(i + j) != target[j]){
                        yes = false;
                        break;
                    }
                }
                if(yes){
                    for(int j = 0; j < target.length; j++){
                        chars.set(i + j, replacement[i]);
                    }
                }
            }
        }
        return this;
    }

    /**
     *
     * @param regex
     * @return Splits this string around matches of the given regular expression.
     */
    public MyString[] split(MyString regex){
        List<MyString> list = new LinkedList<>();
        MyString current = new MyString();
        for(int i = 0; i < length(); i++){
            if(regex.equals(charAt(i))){
                if(current.length() != 0){
                    list.add(current);
                }
                current = new MyString();
            }else{
                current.concat(new MyString(charAt(i)));
            }
        }
        MyString[] strings = new MyString[list.size()];
        for(int i = 0; i < list.size(); i++){
            strings[i] = list.get(i);
        }
        return strings;
    }

    /**
     *
     * @param prefix
     * @return Tests if this string starts with the specified prefix.
     */
    public boolean startsWith(MyString prefix){
        if(prefix.length() > length()) return false;
        for(int i = 0; i < prefix.length(); i++){
            if(prefix.charAt(i) != charAt(i)) return false;
        }
        return true;
    }

    public MyString substring(int from){
        // TODO
        return this;
    }

    public MyString substring(int from, int to){
        // TODO
        return this;
    }

    /**
     *
     * @param str
     * @return Converts this string to a new character array.
     */
    public char[] toCharArray(MyString str){
        char[] ans = new char[str.length()];
        for(int i = 0; i < str.length(); i++){
            ans[i] = str.charAt(i);
        }
        return ans;
    }

    /**
     *
     * @return Returns this String only with lower case letters
     */
    public MyString toLowerCase(){
        for(int i = 0; i < chars.size(); i++){
            if(Character.isUpperCase(chars.get(i))){
                char c = (char) (chars.get(i) - 'A' + 'a');
                chars.set(i, c);
            }
        }
        return this;
    }

    /**
     *
     * @return Returns this String only with upper case letters
     */
    public MyString toUpperCase(){
        for(int i = 0; i < chars.size(); i++){
            if(Character.isLowerCase(chars.get(i))){
                char c = (char) (chars.get(i) - 'a' + 'A');
                chars.set(i, c);
            }
        }
        return this;
    }

    /**
     *
     * @return This object is returned as String
     */
    public String toString(){
        return chars.toString();
    }

    public MyString trim(){
        // TODO
        return this;
    }

    public static MyString valueOf(boolean b){
        // TODO
        return new MyString();
    }

    public static MyString valueOf(char c){
        // TODO
        return new MyString();
    }

    public static MyString valueOf(char[] c){
        // TODO
        return new MyString();
    }

    public static MyString valueOf(double d){
        // TODO
        return new MyString();
    }

    public static MyString valueOf(float f){
        // TODO
        return new MyString();
    }

    public static MyString valueOf(int t){
        // TODO
        return new MyString();
    }

    public static MyString valueOf(long l){
        // TODO
        return new MyString();
    }


    /**
     * Helper Methods
     */

    /**
     * Helper Method for indexOf
     * @param str
     * @param from
     * @return
     */
    private int indexOfHelper(MyString str, int from){
        for(int i = from; i < length(); i++){
            if(charAt(i) == str.charAt(0)){
                boolean yes = true;
                if(i + str.length() - 1 >= length()) return -1;
                for(int j = 0; j < str.length(); j++){
                    if(chars.get(i + j) != str.chars.get(j)){
                        yes = false;
                        break;
                    }
                }
                if(yes) return i;
            }
        }
        return -1;
    }

    /**
     * Helper Method for lastIndexOf
     * @param str
     * @param from
     * @return
     */
    private int lastIndexOfHelper(MyString str, int from){
        for(int i = length() - 1; i >= from; i--){
            if(charAt(i) == str.charAt(str.length() - 1)){
                if(i - str.length() + 1 < 0) return -1;
                boolean yes = true;
                for(int j = 0; j < str.length(); j++){
                    if(chars.get(i - j) != str.chars.get(str.length() - j - 1)){
                        yes = false;
                        break;
                    }
                }
                if(yes) return i - str.length() + 1;
            }
        }
        return -1;
    }
}
