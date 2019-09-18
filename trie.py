import time
from collections import deque

words_list = ["fo", "foo", "bar", "foobar", "high", "way", "super", "highway",
              "superhighway", "to", "get", "her", "together"]

new_words = [w for w in open("/usr/share/dict/words", "r").read().split()]

memo = {}


class TrieNode:

    def __init__(self):
        self.children = {}
        self.isEndOfWord = False


class Trie:

    def __init__(self):
        self.root = self.getNode()

    def getNode(self):
        return TrieNode()

    def insert(self, word):
        pCrawl = self.root
        for letter in word:
            if letter not in pCrawl.children:
                pCrawl.children[letter] = self.getNode()
            pCrawl = pCrawl.children[letter]
        pCrawl.isEndOfWord = True

    def search(self, word):
        pCrawl = self.root
        for letter in word:
            if letter not in pCrawl.children:
                return False
            pCrawl = pCrawl.children[letter]
        return pCrawl is not None and pCrawl.isEndOfWord


tree = Trie()


def trie_solver(words: list, tree: Trie()):
    for word in words:
        if len(word) > 1:
            tree.insert(word.lower())


def list_solver(words: list, tree: Trie()):
    place = 0
    count = 1
    for word in words:
        i = 0
        stack = deque()
        while place < len(word):
            i += 1
            if i > 100:  # ensures that no word is more than 100 chars
                break
            step = word[place:place + count].lower()
            if step is word:
                break
            elif tree.search(step):
                stack.append(step)
                place += count
                count = 1
            elif (place + count) is len(word) and len(stack) > 0:
                last = stack.pop()
                place -= len(last)
                count = len(last) + 1
            else:
                count += 1
        if len(stack) > 1:
            for i in range(0, len(stack)):
                memo.setdefault(word, []).append(stack.pop())
        place = 0


def method_time(method_to_run, *args):
    start_time = time.time()
    method_to_run(*args)
    print(method_to_run.__name__, "Runtime: %s" % (time.time() - start_time))


method_time(trie_solver, new_words[0:100000], tree)
method_time(list_solver, new_words[0:100000], tree)

# method_time(trie_solver, "trie_solver", words_list, tree)
# method_time(list_solver, "list_solver", words_list, tree)

print(memo)
