class ChangeColumnDefaultToClosedOfMissions < ActiveRecord::Migration
  def change
    change_column_default :missions, :closed, :false
  end
end
