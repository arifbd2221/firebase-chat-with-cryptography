package onetimepad;

import android.util.Log;

public class OneTimePad {

    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String msg, StringBuilder key){

        String keycopy = key.toString();
        Log.e("stage1msg",msg);
        int k_l = key.length();
        int msg_l = msg.length();

        //msg.toLowerCase();
        //key.toString().toLowerCase();

        if ( k_l > msg_l){
            key =  new StringBuilder(key.substring(0,msg_l));
            return commonEnc(msg_l,msg,key);
        }
        else if ( k_l < msg_l){

            int i= 0;

            while ( k_l != msg_l){

                if (i == keycopy.length())
                    i=0;

                key.append(keycopy.charAt(i));
                k_l++;

                if (i < keycopy.length() )
                    i++;
                else if (i == k_l-1)
                    i--;

            }

            key.toString();
            return commonEnc(msg_l,msg,key);

        }
        else if ( k_l == msg_l){

            return commonEnc(msg_l,msg,key);

        }


        return null;
    }


    static String commonEnc(int msg_l,String msg ,StringBuilder key){

        StringBuilder cipherText = new StringBuilder();

        int v=0;

        Log.e("stage2msg",msg);

        for (int l=0; l < msg_l; l++) {

            v = ALPHABET.indexOf(msg.charAt(l)) + ALPHABET.indexOf(key.charAt(l));
            System.out.println(v);

            if (v > 25)
                cipherText.append((ALPHABET.charAt((v % 26))));
            else if (v <0)
                cipherText.append((ALPHABET.charAt((v + 26))));
            else
                cipherText.append((ALPHABET.charAt(v)));


        }

        Log.e("stage1cipherText",cipherText.toString());

        return cipherText.toString();
    }

   public static String decrypt(String cipherText, StringBuilder key){

        String keycopy = key.toString();

        int k_l = key.length();
        int cipherText_l = cipherText.length();


        if ( k_l > cipherText_l){

            key =  new StringBuilder(key.substring(0,cipherText_l));

            return commonDec(cipherText_l,cipherText,key);
        }
        else if ( k_l < cipherText_l){

            int i= 0;

            while ( k_l != cipherText_l){

                if (i == keycopy.length())
                    i=0;

                key.append(keycopy.charAt(i));
                k_l++;

                if (i < keycopy.length() )
                    i++;
                else if (i == k_l-1)
                    i--;

            }

            key.toString();
            return commonDec(cipherText_l,cipherText,key);

        }
        else if ( k_l == cipherText_l){

            return commonDec(cipherText_l,cipherText,key);

        }

        return null;

    }

    static String commonDec(int msg_l,String msg ,StringBuilder key){

        StringBuilder cipherText = new StringBuilder();

        int v=0;
        for (int l=0; l < msg_l; l++) {

            v = ALPHABET.indexOf(msg.charAt(l)) - ALPHABET.indexOf(key.charAt(l));
            System.out.println(v);

            if (v > 25)
                cipherText.append((ALPHABET.charAt((v % 26))));
            else if (v <0)
                cipherText.append((ALPHABET.charAt((v + 26))));
            else
                cipherText.append((ALPHABET.charAt(v)));

        }

        return cipherText.toString();
    }

}
