import time


def for_calculate_pi(n_terms: int) -> float:
    numerator = 4.0
    demoninator = 1.0
    operation = 1.0
    pi = 0.0
    for _ in range(n_terms):
        pi += operation * (numerator / demoninator)
        demoninator += 2.0
        operation *= -1.0
    return pi


def while_calculate_pi(n_terms: int) -> float:
    numerator = 4.0
    demoninator = 1.0
    operation = 1.0
    pi = 0.0
    while n_terms is not 0:
        pi += operation * (numerator / demoninator)
        demoninator += 2.0
        operation *= -1.0
        n_terms -= 1
    return pi


def method_time(method_to_run, *args):
    start_time = time.time()
    method_to_run(*args)
    final_time = time.time() - start_time
    print(method_to_run.__name__, "Runtime: %s" % (final_time))


if __name__ == "__main__":
    method_time(for_calculate_pi, 100000000)
    method_time(while_calculate_pi, 100000000)

# 0.13 with for loop
# 0.18 with while loop
# Conclusion: for loops are marginally faster in python, significantly faster
# as size increases
