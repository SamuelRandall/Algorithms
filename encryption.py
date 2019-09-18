from secrets import token_bytes
from typing import Tuple


def random_key(length: int) -> int:
    tb = token_bytes(length)
    return int.from_bytes(tb, "big")


def encrypt(original: str) -> Tuple[int, int]:
    original_bytes = original.encode()
    dummy = random_key(len(original_bytes))
    original_key = int.from_bytes(original_bytes, "big")
    encrypted = original_key ^ dummy
    return dummy, encrypted


def decrypt(key1: int, key2: int) -> str:
    decrypted = key1 ^ key2
    temp = decrypted.to_bytes((decrypted.bit_length() +
                               7) // 8, "big")
    return temp.decode()


if __name__ == "__main__":
    key1, key2 = encrypt("One Time Pad!")
    result = decrypt(key1, key2)
    print(result)
