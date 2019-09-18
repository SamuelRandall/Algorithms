import time
# Given a list of words, identify all words in the list that can be combined to
# form words within the same list

words_list = ["foo", "bar", "foobar", "high", "way", "super", "highway",
              "superhighway", "to", "get", "her", "together"]

f = open("words.txt", "r")
content = f.read()
new_words = [word.lower() for word in content.split()]

# Result = {"foobar": ["foo", "bar"], "superhighway": ["super", "high", "way"],
#           "together": ["to", "get", "her"]}
#
# Bonus = {"foobar": [["foo", "bar"]], "superhighway": [["super", "high",
# "way"]  , ["super", "highway"]], "together": [["to", "get", "her"]]}
memo = {}


def nested_solver(words: list):
    for outer_word in words:
        for inner_word in words:
            if inner_word in outer_word and inner_word != outer_word:
                memo.setdefault(outer_word, []).append(inner_word)


# trie_solver(words_list)
start_time = time.time()

# nested_solver(new_words[0:10000])
nested_solver(new_words[0:10000])
print("--- %s seconds ---" % (time.time() - start_time))
print(memo)
