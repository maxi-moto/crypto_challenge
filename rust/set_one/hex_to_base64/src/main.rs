extern crate rustc_serialize;

use std::env;
use rustc_serialize::base64::{ToBase64, STANDARD};
use rustc_serialize::hex::{ToHex, FromHex};

fn process_args(args: Vec<String>) {

    if args[1] == "hex" { 
        convert_to_base64(args[2].clone()) 
    } else { 
        convert_to_hex(args[1].clone())
    };
}

fn convert_to_hex(input_string: String) {
    let hex_string = input_string.as_bytes().to_hex();
    println!("hex -> {}", hex_string);
}

fn convert_to_base64(hex_string: String) {
    let base64_string = hex_string.from_hex().unwrap().to_base64(STANDARD);
    println!("Base64 -> {}", base64_string);
}

fn main() {
    //The strings are...
    //hex:      49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d
    //base64:   SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t
    let args: Vec<String> = env::args().collect();

    process_args(args);
}
