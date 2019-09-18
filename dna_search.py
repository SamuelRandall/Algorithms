from enum import IntEnum
from typing import Tuple, List
import time

def time_method(method_to_run, *args):
    start_time = time.time()
    method_to_run(*args)
    print(method_to_run.__name__, "Runtime: %s" % (time.time() - start_time))

Nucleotide: IntEnum = IntEnum('Nucleotide', ('A', 'C', 'G', 'T'))
Codon = Tuple[Nucleotide, Nucleotide, Nucleotide]
Gene = List[Codon]

gene_str: str = "ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT"

def string_to_gene(s: str) -> Gene:
    gene: Gene = []
    for i in range(0, len(s), 3):
        if (i + 2) >= len(s):
            return gene
        codon: Codon = (Nucleotide[s[i]], Nucleotide[s[i + 1]], Nucleotide[s[i + 2]])
        gene.append(codon)
    return gene

my_gene: Gene = string_to_gene(gene_str)


def linear_contains(gene: Gene, key_codon: Codon) -> bool:
    for codon in gene:
        if codon == key_codon:
            return True
    return False

acg: Codon = (Nucleotide.A, Nucleotide.C, Nucleotide.G)
gat: Codon = (Nucleotide.G, Nucleotide.A, Nucleotide.T)

print(time_method(linear_contains, my_gene, acg))
print(time_method(linear_contains, my_gene, gat))
# due to the fact that these types have a contain method, this print statement will work without linear_contains
print(acg in my_gene)
print(gat in my_gene)

def binary_contains(gene: Gene, key_codon: Codon) -> bool:
    low: int = 0
    high: int = len(gene) - 1
    while low <= high:
        mid: int = (low + high) // 2
        if gene[mid] < key_codon:
            low = mid + 1
        elif gene[mid] > key_codon:
            high = mid - 1
        else:
            return True
    return False

my_sorted_gene: Gene = sorted(my_gene)
print(time_method(binary_contains, my_sorted_gene, acg))
print(time_method(binary_contains, my_sorted_gene, gat))