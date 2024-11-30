-- Arguments passed: KEYS[1..N] -> product keys; ARGV -> quantity for each product
local numItems = #KEYS
local results = {}

for i = 1, numItems do
    local productKey = KEYS[i]
    local increaseQtyBy = tonumber(ARGV[i])

	-- Increment the field by the given value
	local newQuantity = redis.call('HINCRBY', productKey, "availableQuantity", increaseQtyBy)
	
	table.insert(results, newQuantity)
end
	
return results
