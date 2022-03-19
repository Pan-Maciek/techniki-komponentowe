namespace TextSearch.Misc; 

public static class StringHelper {
    public static List<int>? IndicesOf(this string input, string value, StringComparison comparisonType) {
        var index = input.IndexOf(value, comparisonType);
        if (index == -1) return null;
        var indices = new List<int>();
        
        while (index >= 0 && index < input.Length) {
            indices.Add(index);
            index = input.IndexOf(value, index + 1, comparisonType);
        }

        return indices;
    }
}