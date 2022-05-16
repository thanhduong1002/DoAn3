package utility;


public class CatchError {
	private static CatchError _Instance;
	
	public static CatchError Instance() {
		if(_Instance == null) 
			_Instance = new CatchError();
		return _Instance;
	}
	
	private CatchError() {}

	//Kiểm tra chuỗi số thỏa mãn chỉ là số và bằng đúng dài
	//Nếu độ dài không cố định, truyền tham số DoDai = 0
	public boolean KieuSo(String k, int DoDai) {
		String s = k.trim();
		if(DoDai != 0 && s.length() != DoDai)
			return false;
		for(int i = 0; i < s.length(); ++i) {
			if(s.charAt(i) < '0' || s.charAt(i) > '9')
				return false;
		}
		return true;
	}
	
	public boolean KieuChuoiThuan(String k) {
		String s = k.trim();
		for(int i = 0; i < s.length(); ++i) {
			if((s.charAt(i) < 'A' && s.charAt(i) != ' ' && s.charAt(i) != '	')
					|| (s.charAt(i) > 'Z' && s.charAt(i) < 'a')
					|| (s.charAt(i) > 'z' && s.charAt(i) < 'À'))
				return false;
		}
		return true;
	}
	
}