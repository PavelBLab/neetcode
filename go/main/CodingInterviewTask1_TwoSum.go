package main

import "fmt"

// Given an array of integers nums and an integer target,
// return indices of the two numbers that add up to target.
// You may assume each input has exactly one solution,
// and you may not use the same element twice.
//
// Example 1: nums = [2,7,11,15], target = 9 → [0,1]
// Example 2: nums = [3,2,4], target = 6 → [1,2]
// Example 3: nums = [3,3], target = 6 → [0,1]

func main() {
	fmt.Println(twoSum([]int{2, 7, 11, 15}, 9)) // expect [0,1]
	fmt.Println(twoSum([]int{3, 2, 4}, 6))      // expect [1,2]
	fmt.Println(twoSum([]int{3, 3}, 6))         // expect [0,1]
}

func twoSum(nums []int, target int) []int {
	m := make(map[int]int)

	for i, num := range nums {
		diff := target - num

		if val, ok := m[diff]; ok {
			return []int{val, i}
		}

		m[num] = i
	}

	return nil
}
