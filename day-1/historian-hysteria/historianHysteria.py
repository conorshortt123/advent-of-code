# total = 0
# with open("sample_input.txt", "r") as input:
#     for line in input:
#         linetotal = 0
#         lists = line.split()
#         list1 = sorted(list(lists[0]))
#         list2 = sorted(list(lists[1]))

#         for i in range(5):
#             diff = abs(int(list1[i]) - int(list2[i]))
#             linetotal += diff
#         total += linetotal
# print(total)

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

print(total)