#!/usr/bin/python3
import sys
for x in sys.stdin:
	words = x.split()
	for y in words:
		print(y, 1)

