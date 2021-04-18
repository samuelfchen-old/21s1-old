import sys
 
class Node:
    parent = None
    prev = None
    num = None
 
    def __str__(self):
        return str(self.num).zfill(3)
 
    def __repr__(self):
        return str(self.num).zfill(3)
 
    def __init__(self, parent, num, prev):
        self.parent = parent
        self.num = num
        self.prev = prev
 
    def generate_children(self):
        children = []
 
        for i in range(1,4):
            # print(i)
            n1 = self.mod(i, -1)
            # print(n1)
            n2 = self.mod(i, 1)
            # print(n2)
 
            if n1 != None:
                children.append(n1)
            if n2 != None:
                children.append(n2)
        
        return children
 
    def mod(self, pos, inc):  
        if pos == self.prev:
            # print("matching prev")
            return None
 
        place = self.num // 10 ** (3 - pos) % 10
 
        if place == 9 and inc == 1:
            # print("9")
 
            return None
 
        if place == 0 and inc == -1:
            # print("0")
            return None
 
        new = self.num + inc * 10**(3 - pos)
 
        return Node(self, new, pos)
            
 
    def generate_path(self):
        curr = self
        path = []
        while (curr != None):
            path.insert(0, curr)
            curr = curr.parent
        # path.pop(len(path) - 1)
        return path
 
    def manhattan(self, goal):
        m = 0
        for i in range(1, 4):
            self_place = self.num // 10 ** (3 - i) % 10
            goal_place = goal // 10 ** (3 - i) % 10
 
            m += abs(self_place - goal_place)
 
        return m
 
if __name__ == "__main__":
    # Read the file
    search = sys.argv[1]
 
    f = open(sys.argv[2], "r")
 
    start = int(f.readline())
    goal = int(f.readline())
    strforbidden = f.readline().split(',')
    forbidden = []
    if strforbidden[0] != '':
        for s in strforbidden:
            forbidden.append(int(s))
    
    # Search
    num_expanded = 0
    fringe = []
    expanded = []
    final_expanded = []
    root = Node(None, start, None)
    fringe.append(root)
 
    ids_depth = 0
    
 
    # print(root.generate_children(expanded))
 
    found = None
    while num_expanded < 1000:
        num_expanded += 1
        if len(fringe) == 0:
            if search == "I":
                # print("yes")
                for n in expanded:
                    final_expanded.append(n)
                    # print(final_expanded)
                ids_depth += 1
                fringe = []
                expanded = []
                fringe.append(root)
            else:
                break
 
        # Check if curr is valid
        curr = fringe.pop(0)
        for node in expanded:
            if node.num == curr.num and node.prev == curr.prev:
                curr = None
                break
 
        if curr != None and curr.num in forbidden:
            curr = None
 
        if curr == None:
            num_expanded -= 1
            continue
        else:
            expanded.append(curr)
 
        if curr.num == goal:
            found = curr
            break
        
        if search == "B":
            #BFS
            for n in curr.generate_children():
                fringe.append(n)
        elif search == "D":
            #DFS
            # print(curr.generate_children())
            for n in curr.generate_children()[::-1]:
                fringe.insert(0, n)
        elif search == "I":
            #IDS
            if len(curr.generate_path()) <= ids_depth:
                for n in curr.generate_children()[::-1]:
                    fringe.insert(0, n)
        elif search == "G":
            #Greedy
            for n in curr.generate_children():
                fringe.append(n)
 
            # find the min and move to start of list
            min_node = fringe[0]
            for n in fringe:
                if n.manhattan(goal) <= min_node.manhattan(goal) and not (n.num in forbidden):
                    min_node = n
            fringe.remove(min_node)
            fringe.insert(0, min_node)
 
        elif search == "A":
            #A*
            for n in curr.generate_children():
                fringe.append(n)
 
            # find the min and move to start of list
            min_node = fringe[0]
            for n in fringe:
                if n.manhattan(goal) + len(n.generate_path()) <= min_node.manhattan(goal) + len(min_node.generate_path()) and not (n.num in forbidden):
                    min_node = n
            fringe.remove(min_node)
            fringe.insert(0, min_node)
            pass
        elif search == "H":
            #Hill Climbing
            children = curr.generate_children()
            min_node = curr
            for n in curr.generate_children():
                if n.manhattan(goal) <= min_node.manhattan(goal):
                    min_node = n
            fringe = []
            fringe.append(min_node)
 
    # Print results
    for n in expanded:
        final_expanded.append(n)
 
    if found == None:
        print("No solution found.")
    else:
        print(','.join(map(str, found.generate_path())))
    print(','.join(map(str, final_expanded)))