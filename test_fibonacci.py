import unittest
from fibonacci import fib1, fib2, fib3, fib4, fib5, fib6, fib7


class TestFibonacci(unittest.TestCase):

    def test_fib1(self):
        with self.assertRaises(RecursionError):
            print(fib1(5))

    def test_fib2(self):
        self.assertEqual(fib2(1), 1)

    def test_fib3(self):
        self.assertEqual(fib3(1), 1)

    def test_fib4(self):
        self.assertEqual(fib4(50), 12586269025)

    def test_fib5(self):
        self.assertEqual(fib5(50), 12586269025)

    # their solution actually yields the incorrect result: always +1
    def test_fib6(self):
        self.assertEqual(sum(list(fib6(50))), 12586269025)

    def test_fib7(self):
        self.assertEqual(sum(fib7(50)), 12586269025)


if __name__ == '__main__':
    unittest.main()
