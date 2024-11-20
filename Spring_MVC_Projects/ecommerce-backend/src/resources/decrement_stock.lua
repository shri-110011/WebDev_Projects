-- Arguments passed: KEYS[1..N] -> product keys; ARGV -> pairs of quantity and price for each product
local numItems = #KEYS
local results = {}

for i = 1, numItems do
    local productKey = KEYS[i]
    local requiredQty = tonumber(ARGV[(i - 1) * 2 + 1])
    local requestedPrice = ARGV[(i - 1) * 2 + 2]

    local currentQty = tonumber(redis.call('HGET', productKey, 'availableQuantity'))
    local price = redis.call('HGET', productKey, 'price')

    if currentQty >= requiredQty then
		if price == requestedPrice then
        	redis.call('HINCRBY', productKey, 'availableQuantity', -requiredQty)
			table.insert(results, '2') -- Success for this item
		else
			table.insert(results, '1')
			table.insert(results, price)
		end
    else
		table.insert(results, '0')
		if currentQty == 0 then
			table.insert(results, 'Out of stock')
		elseif currentQty <= 10 then
			table.insert(results, 'Only ' .. currentQty .. ' left in stock')
		else
			table.insert(results, 'Insufficient stock')
      	end
    end
end

return results
