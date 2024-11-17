-- Arguments passed: KEYS[1..N] -> product keys; ARGV -> pairs of quantity and price for each product
local numItems = #KEYS
local results = {}

for i = 1, numItems do
    local productKey = KEYS[i]
    local requiredQty = tonumber(ARGV[(i - 1) * 2 + 1])
    local requiredPrice = tonumber(ARGV[(i - 1) * 2 + 2])

    local currentQty = tonumber(redis.call('HGET', productKey, 'availableQuantity'))
    local price = tonumber(redis.call('HGET', productKey, 'price'))

    if currentQty >= requiredQty then
		if price == requiredPrice then
        	redis.call('HINCRBY', productKey, 'availableQuantity', -requiredQty)
        	table.insert(results, 2)  -- Success for this item
		else
			table.insert(results, 1)
		end
    else
        table.insert(results, 0)  -- Failure for this item
    end
end
return results
