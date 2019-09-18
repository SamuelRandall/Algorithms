import unittest
from words_combination import memo


class TestWordsCombination(unittest.TestCase):

    def test_words_list_solver(self):
        self.assertTrue(set({"foobar": ["foo", "bar"], "superhighway": ["super", "high", "way"],
                             "together": ["to", "get", "her"]}).issubset(memo))


if __name__ == '__main__':
    unittest.main()
