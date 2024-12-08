def part_one():
    lines = []

    with open("input.txt") as file:
        lines = file.readlines()

    first_list = []
    second_list = []

    for i in range(len(lines)):
        first_list.append(int(lines[i].split()[0]))
        second_list.append(int(lines[i].split()[1]))

    first_list.sort()
    second_list.sort()

    total = 0
    for i in range(len(first_list)):
        total += abs(first_list[i] - second_list[i])

    return total

def part_two():
    with open("input.txt") as file:
        lines = file.readlines()

    right_frequency_map = {}
    left_list = []
    similarityScore = 0

    for i in range(len(lines)):
        left_list.append(int(lines[i].split()[0]))
        right = int(lines[i].split()[1])
        right_frequency_map.update({right : right_frequency_map.get(right, 0) + 1})

    for i in left_list:
        similarityScore += i * right_frequency_map.get(i, 0)

    return similarityScore


print(part_one())
print(part_two())