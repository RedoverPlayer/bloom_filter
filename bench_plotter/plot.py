import json
import matplotlib.pyplot as plt

if __name__ == "__main__":
    # init data structure to store benchmark data
    benchData = {
        "BloomBenchmark.arrayListAdd": {},
        "BloomBenchmark.arrayListContains": {},
        "BloomBenchmark.linkedListAdd": {},
        "BloomBenchmark.linkedListContains": {},
        "BloomBenchmark.tabAdd": {},
        "BloomBenchmark.tabContains": {},
    }

    # read benchmark data from file
    f = open("bench.txt", "r")
    for line in f:
        line = line.replace(",", ".")

        # replace multiple spaces with one to help split
        line = " ".join(line.split())
        line = line.split(" ")
        benchData[line[0]][float(line[1])] = float(line[4])

    # shows output json (for debugging)
    print(json.dumps(benchData, indent=4))

    f.close()

    # change style of lines to better distinguish them
    lineStyles = ['solid', 'dashed', 'dashdot', 'dotted', 'solid', 'dashed']
    # set very distinct colors to better distinguish them
    colors = ['red', 'green', 'gray', 'orange', 'blue', 'black']

    # plot data
    i = 0
    for key in benchData:
        plt.plot(benchData[key].keys(), benchData[key].values(), label = key, linestyle = lineStyles[i], color = colors[i])
        i += 1

    # adds labels, legend and shows plot
    plt.xlabel('Number of elements')
    plt.ylabel('Time in us')
    plt.legend()
    plt.show()
