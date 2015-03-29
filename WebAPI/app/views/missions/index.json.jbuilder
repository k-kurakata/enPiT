json.array!(@missions) do |mission|
  json.extract! mission, :id, :body, :difficulty, :closed
  json.url mission_url(mission, format: :json)
end
