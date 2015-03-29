# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rake db:seed (or created alongside the db with db:setup).
#
# Examples:
#
#   cities = City.create([{ name: 'Chicago' }, { name: 'Copenhagen' }])
#   Mayor.create(name: 'Emanuel', city: cities.first)

Product.create([
  {name: 'King Cobra Lv.4: Move Robot Program', price: 4, document: false, feature_change_token: 'Ks9ga2Ni'},
  {name: 'King Cobra Lv.3: Head', price: 3, document: true},
  {name: 'King Cobra Lv.2: Neck', price: 2, document: true},
  {name: 'King Cobra Lv.1: Body and tail', price: 1, document: true},
  {name: 'Tank with tablet Lv.4: Move Robot Program', price: 4, document: false, feature_change_token: 'WfPgDjGL'},
  {name: 'Tank with tablet Lv.3: Head', price: 3, document: true},
  {name: 'Tank with tablet Lv.2: Neck', price: 2, document: true},
  {name: 'Tank with tablet Lv.1: Body and tail', price: 1, document: true},
  {name: 'Car with tablet Lv.4: Move Robot Program', price: 4, document: false, feature_change_token: 'ttgeCxQL'},
  {name: 'Car with tablet Lv.3: Head', price: 3, document: true},
  {name: 'Car with tablet Lv.2: Neck', price: 2, document: true},
  {name: 'Car with tablet Lv.1: Body and tail', price: 1, document: true},
])
