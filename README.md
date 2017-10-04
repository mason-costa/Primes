# prime-table

Description: A command line application which prints out a multiplication table of the first n prime numbers. N is supplied as a command line argument.

## Usage

Run with Leiningen

    $ lein run 10

## Rationale

For the base task, simply hardcoding the vector (2 3 5 7 11 13 17 19 23 27) would have been easy enough. But this is obviously not a viable solution for any sort of real-world, or even toy, application. So the task became how best to generate primes numbers. The best algorithm for the job seemed to be the [Sieve of Eratosthenes](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes). I chose to optimize the algorithm by capping my divisions at the square root of the upper limit of the numbers. This is a rather straight forward optimization and cuts down on the number of operations by a pretty large factor.

The only major shortcoming of this approach is that it relies on an upper bound and generating a sequence of numbers up to that number. Without hardcoding limits, there's no way to tell how high the limit should be set. To solve that, I wrote a companion function that will try to get the first n prime numbers. If the default set of primes, up to 121, is insufficient, it calls the sieve again; this time with a collection of numbers for it to operate on. This collection is built by concating the list of primes from the previous sieve with the next hundred whole numbers. The sieve will run again on this new set of numbers and check if the n primes are present. This process is repeated until the number of primes is delivered.

## Considerations 

The algorithm runs very quickly for n < 100. As n grows, so too does the running time and complexity. The bulk of procesing time is spent finding the prime numbers. The sieve scales vertically, but the application isn't designed with parallel processing in mind. The complexity of coordinating messagig across nodes in a clister seemed to be a gross over-engineering for this task.
