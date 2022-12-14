import json
import matplotlib.pyplot as plt

benchData = {
    "BloomBenchmark.arrayListAdd": {},
    "BloomBenchmark.arrayListContains": {},
    "BloomBenchmark.linkedListAdd": {},
    "BloomBenchmark.linkedListContains": {},
    "BloomBenchmark.tabAdd": {},
    "BloomBenchmark.tabContains": {},
}

f = open("bench.txt", "r")
for line in f:
    line = line.replace(",", ".")
    # replace multiple spaces with one
    line = " ".join(line.split())
    line = line.split(" ")
    benchData[line[0]][float(line[1])] = float(line[4])

print(json.dumps(benchData, indent=4))

f.close()

lineStyles = ['solid', 'dashed', 'dashdot', 'dotted', 'solid', 'dashed']
colors = ['red', 'green', 'gray', 'orange', 'blue', 'black']
i = 0

for key in benchData:
    plt.plot(benchData[key].keys(), benchData[key].values(), label = key, linestyle = lineStyles[i], color = colors[i])
    i += 1
plt.xlabel('Number of elements')
plt.ylabel('Time in us')
plt.legend()
plt.show()
