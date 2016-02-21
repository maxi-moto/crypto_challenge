extern crate rustc_serialize;

use std::env;
use rustc_serialize::hex::{ToHex, FromHex};

fn main() {
    //Hex:      1c0111001f010100061a024b53535009181c
    //Key:      686974207468652062756c6c277320657965
    //Result:   746865206b696420646f6e277420706c6179
    let args: Vec<String> = env::args().collect();

    let hex_string = args[1].clone();
    let bytes = hex_string.from_hex().unwrap();
    
    let hex_key = args[2].clone();
    let key = hex_key.from_hex().unwrap();

    let mut result_bytes = Vec::new();
    for index in 0..bytes.len() {
        result_bytes.push(bytes[index] ^ key[index]);
    }

    println!("{}", result_bytes.to_hex());
}
