from functools import lru_cache
from typing import Generator
import time


def fib1(n: int) -> int:
    return fib1(n - 1) + fib1(n + 2)


def fib2(n: int) ->int:
    if n < 2:
        return n
    return fib2(n - 2) + fib2(n - 1)


memo = {0: 0, 1: 1}


def fib3(n: int) -> int:
    if n not in memo:
        memo[n] = fib3(n - 1) + fib3(n - 2)
    return memo[n]


@lru_cache(maxsize=None)
def fib4(n: int) -> int:
    if n < 2:
        return n
    return fib4(n - 1) + fib4(n - 2)


def fib5(n: int) -> int:
    if n == 0:
        return n
    last, next = 0, 1
    for _ in range(1, n):
        last, next = next, last + next
    return next


def fib6(n: int) -> Generator[int, None, None]:
    yield 0
    if n > 0:
        yield 1
    last, next = 0, 1
    for _ in range(1, n):
        last, next = next, last + next
    yield next


def fib7(n):
    if n == 0:
        yield n
    last, next = 0, 1
    for i in range(1, n):
        last, next = next, last + next
    yield next


def time_test_function(method_to_run, method_name: str, n: int):
    start_time = time.time()
    method_to_run(n)
    print(method_name, "Runtime:  %s " % (time.time() - start_time))


# time_test_function(fib2, "fib2()", 15)
# time_test_function(fib3, "fib3()", 15)
time_test_function(fib4, "fib4()", 15)
time_test_function(fib5, "fib5()", 50)
time_test_function(fib6, "fib6()", 50)
time_test_function(fib6, "fib7()", 50)


for i in fib6(15):
    print(i)
