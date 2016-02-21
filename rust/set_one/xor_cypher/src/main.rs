extern crate rustc_serialize;

use std::env;
use rustc_serialize::hex::FromHex;

fn decode_string(ascii_key: u8, hex_string: &String) -> String {
    let byte_string = hex_string.from_hex().unwrap();

    let mut decoded_string = Vec::new();
    for byte in 0..byte_string.len() {
        decoded_string.push(byte_string[byte] ^ ascii_key);
    }

    return String::from_utf8(decoded_string).unwrap();
}

fn print_likely_string(ascii_key: u8, decoded_string: String) {
    let mut character_count = 0;
    let mut has_space = false;
    for character in decoded_string.chars() {
        if character >= 'a' && character <= 'z' { 
            character_count += 1;
        }

        if character.is_whitespace() {
            has_space = true;
        }
    }

    if character_count > 10 && has_space == true {
        println!("'{}' -> {}", ascii_key as char, decoded_string);
    }
}

fn main() {
    //Encoded string:   1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736
    
    let args: Vec<String> = env::args().collect();
    let hex_string = args[1].clone();

    let ascii_keys: u8 = 127; 
    for ascii_key in 0..ascii_keys {
        print_likely_string(ascii_key, decode_string(ascii_key, &hex_string));
    }
}
